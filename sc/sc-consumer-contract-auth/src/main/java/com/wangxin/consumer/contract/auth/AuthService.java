package com.wangxin.consumer.contract.auth;



import com.wangxin.consumer.contract.auth.dto.PermissionDto;
import com.wangxin.consumer.contract.auth.dto.RoleDto;
import com.wangxin.consumer.contract.auth.dto.UserDto;

import java.util.List;

public interface AuthService {
    UserDto findUserByName(String username);
    List<RoleDto> findRoleByUserId(String userId);
    List<PermissionDto> getPermissions(String userId);
}
