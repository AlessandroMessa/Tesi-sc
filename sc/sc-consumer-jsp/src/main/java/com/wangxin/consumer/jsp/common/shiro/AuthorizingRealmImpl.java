package com.wangxin.consumer.jsp.common.shiro;

import com.wangxin.consumer.contract.auth.dto.ConsumerConstants;
import com.wangxin.consumer.contract.auth.dto.PermissionDto;
import com.wangxin.consumer.contract.auth.dto.RoleDto;
import com.wangxin.consumer.contract.auth.dto.UserDto;
import com.wangxin.consumer.contract.auth.exception.InvalidParameterException;
import com.wangxin.consumer.contract.auth.facade.AuthenticationFacade;
import com.wangxin.consumer.jsp.common.shiro.vo.Principal;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AuthorizingRealmImpl extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(AuthorizingRealmImpl.class);
    @Autowired
    private AuthenticationFacade authenticationFacade;


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        if (StringUtils.isBlank(username)) {
            log.error("Illegal login attempt: blank username");
            throw new InvalidParameterException("user.illegal.login.error");
        }

        UserDto user = authenticationFacade.findUserByName(username);
        if (user == null) {
            log.error("User not found: {}", username);
            throw new InvalidParameterException("user.login.error");
        }

        byte[] salt = authenticationFacade.decodeHex(user.getSaltHex());

        Principal principal = new Principal();
        principal.setUser(user);
        principal.setRoles(authenticationFacade.findRoleByUserId(user.getId()));

        return new SimpleAuthenticationInfo(
                principal,
                user.getPasswordHash(),
                ByteSource.Util.bytes(salt),
                getName()
        );
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Collection<Principal> fromRealm = principals.fromRealm(getName());
        if (fromRealm == null || fromRealm.isEmpty()) {
            throw new AuthorizationException("No principals found for realm " + getName());
        }
        Principal principal = fromRealm.iterator().next();

        Session session = SecurityUtils.getSubject().getSession();

        @SuppressWarnings("unchecked")
        Set<String> permissions = (Set<String>) session.getAttribute(ConsumerConstants.PERMISSION_URL);
        if (permissions == null) {
            permissions = new HashSet<String>();
            for (PermissionDto p : authenticationFacade.getPermissions(principal.getUser().getId())) {
                permissions.add(p.getUrl());
                if (CollectionUtils.isNotEmpty(p.getChildren())) {
                    for (PermissionDto c : p.getChildren()) {
                        permissions.add(c.getUrl());
                    }
                }
            }
            session.setAttribute(ConsumerConstants.PERMISSION_URL, permissions);
        }

        @SuppressWarnings("unchecked")
        Set<String> roleCodes = (Set<String>) session.getAttribute(ConsumerConstants.ROLE_CODE);
        if (roleCodes == null) {
            roleCodes = new HashSet<String>();
            for (RoleDto r : authenticationFacade.findRoleByUserId(principal.getUser().getId())) {
                roleCodes.add(r.getCode());
            }
            session.setAttribute(ConsumerConstants.ROLE_CODE, roleCodes);
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleCodes);
        info.setStringPermissions(permissions);
        return info;
    }

    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("SHA-1");
        matcher.setHashIterations(1024);
        setCredentialsMatcher(matcher);
    }
}
