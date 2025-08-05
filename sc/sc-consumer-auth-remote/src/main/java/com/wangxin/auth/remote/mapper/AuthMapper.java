// com.wangxin.consumer.service.auth.mapper.AuthMapper.java
package com.wangxin.auth.remote.mapper;

import com.wangxin.common.api.model.auth.PermissionVo;
import com.wangxin.common.api.model.auth.Role;
import com.wangxin.common.api.model.auth.User;
import com.wangxin.consumer.contract.auth.dto.PermissionDto;
import com.wangxin.consumer.contract.auth.dto.RoleDto;
import com.wangxin.consumer.contract.auth.dto.UserDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AuthMapper {

    public static UserDto toDto(User src) {
        if (src == null) return null;
        UserDto dto = new UserDto();
        dto.setId(src.getId());
        dto.setUsername(src.getUsername());
        dto.setPasswordHash(src.getPassword());
        dto.setSaltHex(src.getSalt());
        dto.setTrueName(src.getTrueName());
        // mappa altri campi se servono
        return dto;
    }

    public static RoleDto toDto(Role src) {
        if (src == null) return null;
        RoleDto dto = new RoleDto();
        dto.setCode(src.getCode());
        // mappa altri
        return dto;
    }

    public static PermissionDto toDto(PermissionVo src) {
        if (src == null) return null;
        PermissionDto dto = new PermissionDto();
        dto.setUrl(src.getUrl());
        if (src.getChildren() != null) {
            List<PermissionDto> children = src.getChildren().stream()
                    .map(AuthMapper::toDto)
                    .collect(Collectors.toList());
            dto.setChildren(children);
        }
        return dto;
    }

    public static List<RoleDto> toRoleDtoList(List<Role> src) {
        if (src == null) {
            return Collections.emptyList();
        }
        return src.stream()
                .map(AuthMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<PermissionDto> toPermissionDtoList(List<PermissionVo> src) {
        if (src == null) {
            return Collections.emptyList();
        }
        return src.stream()
                .map(AuthMapper::toDto)
                .collect(Collectors.toList());
    }
    public static User toRemote(UserDto src) {
        if (src == null) return null;
        User user = new User();
        user.setId(src.getId());
        user.setUsername(src.getUsername());
        user.setPassword(src.getPasswordHash());
        user.setSalt(src.getSaltHex());
        // se ci sono altri campi, mappali qui
        return user;
    }
    /** Mappa una lista di RoleDto in una lista di Role per il remoto */
    public static List<Role> toRoleRemoteList(List<RoleDto> src) {
        if (src == null) return Collections.emptyList();
        return src.stream()
                .map(dto -> {
                    Role r = new Role();
                    r.setCode(dto.getCode());
                    // altri campi
                    return r;
                })
                .collect(Collectors.toList());
    }
}
