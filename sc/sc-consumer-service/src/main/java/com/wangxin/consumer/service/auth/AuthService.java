package com.wangxin.consumer.service.auth;

import com.wangxin.common.api.model.auth.PermissionVo;
import com.wangxin.common.api.model.auth.Role;
import com.wangxin.common.api.model.auth.User;
import com.wangxin.consumer.service.auth.dto.PermissionDto;
import com.wangxin.consumer.service.auth.dto.RoleDto;
import com.wangxin.consumer.service.auth.dto.UserDto;

import java.util.List;

public interface AuthService {
    UserDto findUserByName(String username);
    List<RoleDto> findRoleByUserId(String userId);
    List<PermissionDto> getPermissions(String userId);
}
