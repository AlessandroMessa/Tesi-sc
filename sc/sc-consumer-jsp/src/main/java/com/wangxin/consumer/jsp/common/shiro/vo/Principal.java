package com.wangxin.consumer.jsp.common.shiro.vo;

import java.io.Serializable;
import java.util.List;

import com.wangxin.consumer.service.auth.dto.RoleDto;
import com.wangxin.consumer.service.auth.dto.UserDto;

public class Principal implements Serializable {
    private static final long serialVersionUID = -6477583820961243636L;

    private UserDto user;
    private List<RoleDto> roles;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return user.getTrueName();
    }

}
