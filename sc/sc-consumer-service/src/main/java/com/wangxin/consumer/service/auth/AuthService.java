package com.wangxin.consumer.service.auth;

import com.wangxin.common.api.model.auth.PermissionVo;
import com.wangxin.common.api.model.auth.Role;
import com.wangxin.common.api.model.auth.User;

import java.util.List;

public interface AuthService {
    User findUserByName(String username);
    List<Role> findRoleByUserId(String userId);
    List<PermissionVo> getPermissions(String userId);
}
