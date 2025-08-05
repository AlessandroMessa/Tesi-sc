package com.wangxin.consumer.thymeleaf.common.shiro;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;



import com.wangxin.consumer.contract.auth.AuthService;
import com.wangxin.consumer.contract.auth.dto.PermissionDto;
import com.wangxin.consumer.contract.auth.dto.RoleDto;
import com.wangxin.consumer.contract.auth.dto.UserDto;
import com.wangxin.consumer.contract.auth.exception.InvalidParameterException;
import com.wangxin.consumer.service.common.ConsumerConstants;
import com.wangxin.consumer.service.common.SaltService;
import com.wangxin.consumer.thymeleaf.common.shiro.vo.Principal;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vincent.wang
 */
public class AuthorizingRealmImpl extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(AuthorizingRealmImpl.class);

    @Autowired
    private AuthService authService;
    @Autowired
    private SaltService saltService;

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        try {
            if (log.isDebugEnabled()) {
                log.debug("## 正在验证用户登录...");
            }

            UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
            String username = token.getUsername();

            if (StringUtils.isBlank(username)) {
                log.error("## 非法登录 .");
                throw new InvalidParameterException("user.illegal.login.error", "非法用户身份");
            }
            // User user = findUserByName(username);
            UserDto user = authService.findUserByName(username);

            if (null == user) {
                log.error("## 用户不存在={} .", username);
                throw new InvalidParameterException("user.login.error", "账号或密码错误");
            }

            byte[] salt = saltService.decodeHex(user.getSaltHex());

            Principal principal = new Principal();
            principal.setUser(user);
            // principal.setRoles(findRoleByUserId(user.getId()));
            principal.setRoles(authService.findRoleByUserId(user.getId()));

            // SecurityUtils.getSubject().getSession().setAttribute(Constants.PERMISSION_SESSION, getPermissions(user.getId()));
            SecurityUtils.getSubject().getSession().setAttribute(ConsumerConstants.PERMISSION_SESSION, authService.getPermissions(user.getId()));

            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, user.getPasswordHash(), ByteSource.Util.bytes(salt), getName());
            return info;
        } catch (AuthenticationException e) {
            log.error("# doGetAuthenticationInfo error , message={}", e.getMessage());
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @SuppressWarnings("unchecked")
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
        Session session = SecurityUtils.getSubject().getSession();
        // ---
        Set<String> permissions = new HashSet<String>();
        Object permisObj = session.getAttribute(ConsumerConstants.PERMISSION_URL);
        if (null == permisObj) {
            // Collection<PermissionVo> pers = getPermissions(principal.getNews().getId());
            Collection<PermissionDto> pers = authService.getPermissions(principal.getUser().getId());
            for (PermissionDto permission : pers) {
                permissions.add(permission.getUrl());
                if (CollectionUtils.isNotEmpty(permission.getChildren())) {
                    for (PermissionDto childrenPer : permission.getChildren()) {
                        permissions.add(childrenPer.getUrl());
                    }
                }
            }
            session.setAttribute(ConsumerConstants.PERMISSION_URL, permissions);
        } else {
            permissions = (Set<String>) permisObj;
        }

        Set<String> roleCodes = new HashSet<String>();
        Object roleNameObj = session.getAttribute(ConsumerConstants.ROLE_CODE);
        if (null == roleNameObj) {
            // for (Role role : findRoleByUserId(principal.getNews().getId())) {
            for (RoleDto role : authService.findRoleByUserId(principal.getUser().getId())) {
                roleCodes.add(role.getCode());
            }
            session.setAttribute(ConsumerConstants.ROLE_CODE, roleCodes);
        } else {
            roleCodes = (Set<String>) roleNameObj;
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleCodes);
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("SHA-1");
        matcher.setHashIterations(1024);
        setCredentialsMatcher(matcher);
    }

}
