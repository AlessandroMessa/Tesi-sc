package com.wangxin.consumer.contract.auth.facade;

import com.wangxin.consumer.contract.auth.dto.PermissionDto;
import com.wangxin.consumer.contract.auth.dto.RoleDto;
import com.wangxin.consumer.contract.auth.dto.UserDto;

import java.util.List;

public interface AuthenticationFacade {

    public UserDto findUserByName(String username);


    public List<RoleDto> findRoleByUserId(String userId);


    public List<PermissionDto> getPermissions(String userId);


    public byte[] decodeHex(String saltHex);
}
