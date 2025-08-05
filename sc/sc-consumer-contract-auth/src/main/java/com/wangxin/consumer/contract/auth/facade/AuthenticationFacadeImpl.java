package com.wangxin.consumer.contract.auth.facade;

import com.wangxin.consumer.contract.auth.AuthService;
import com.wangxin.consumer.contract.auth.SaltService;
import com.wangxin.consumer.contract.auth.dto.PermissionDto;
import com.wangxin.consumer.contract.auth.dto.RoleDto;
import com.wangxin.consumer.contract.auth.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AuthenticationFacadeImpl implements AuthenticationFacade{

    @Autowired
    private AuthService authService;

    @Autowired
    private SaltService saltService;

    @Override
    public UserDto findUserByName(String username) {
        return authService.findUserByName(username);
    }

    @Override
    public List<RoleDto> findRoleByUserId(String userId) {
        return authService.findRoleByUserId(userId);
    }

    @Override
    public List<PermissionDto> getPermissions(String userId) {
        return authService.getPermissions(userId);
    }

    @Override
    public byte[] decodeHex(String saltHex) {
        return saltService.decodeHex(saltHex);
    }
}
