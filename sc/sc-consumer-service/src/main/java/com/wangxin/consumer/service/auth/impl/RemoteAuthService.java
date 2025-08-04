package com.wangxin.consumer.service.auth.impl;

import com.wangxin.common.api.model.auth.PermissionVo;
import com.wangxin.common.api.model.auth.Role;
import com.wangxin.common.api.model.auth.User;
import com.wangxin.consumer.service.auth.AuthService;
import com.wangxin.consumer.service.auth.dto.PermissionDto;
import com.wangxin.consumer.service.auth.dto.RoleDto;
import com.wangxin.consumer.service.auth.dto.UserDto;
import com.wangxin.consumer.service.auth.mapper.AuthMapper;
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
    public UserDto findUserByName(String username) {
        return AuthMapper.toDto(authRemoteClient.findUserByName(username));
    }

    @Override
    public List<RoleDto> findRoleByUserId(String userId) {
        return AuthMapper.toRoleDtoList(authRemoteClient.findRoleByUserId(userId));
    }

    @Override
    public List<PermissionDto> getPermissions(String userId) {
        return AuthMapper.toPermissionDtoList(authRemoteClient.getPermissions(userId));
    }
}
