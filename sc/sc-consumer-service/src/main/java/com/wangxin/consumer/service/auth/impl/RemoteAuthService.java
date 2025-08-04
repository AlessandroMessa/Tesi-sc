package com.wangxin.consumer.service.auth.impl;

import com.wangxin.common.api.model.auth.PermissionVo;
import com.wangxin.common.api.model.auth.Role;
import com.wangxin.common.api.model.auth.User;
import com.wangxin.consumer.service.auth.AuthService;
import com.wangxin.feign.web.remote.auth.AuthRemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoteAuthService implements AuthService {

    private final AuthRemoteClient authRemoteClient;

    @Autowired
    public RemoteAuthService(AuthRemoteClient authRemoteClient) {
        this.authRemoteClient = authRemoteClient;
    }

    @Override
    public User findUserByName(String username) {
        return authRemoteClient.findUserByName(username);
    }

    @Override
    public List<Role> findRoleByUserId(String userId) {
        return authRemoteClient.findRoleByUserId(userId);
    }

    @Override
    public List<PermissionVo> getPermissions(String userId) {
        return authRemoteClient.getPermissions(userId);
    }
}
