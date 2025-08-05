package com.wangxin.auth.remote;


import com.wangxin.auth.remote.client.AuthRemoteClient;
import com.wangxin.auth.remote.mapper.AuthMapper;
import com.wangxin.consumer.contract.auth.AuthService;
import com.wangxin.consumer.contract.auth.dto.PermissionDto;
import com.wangxin.consumer.contract.auth.dto.RoleDto;
import com.wangxin.consumer.contract.auth.dto.UserDto;
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
