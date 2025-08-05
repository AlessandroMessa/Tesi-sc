package com.wangxin.consumer.thymeleaf.common.shiro.vo;

import com.wangxin.consumer.contract.auth.dto.RoleDto;
import com.wangxin.consumer.contract.auth.dto.UserDto;

import java.io.Serializable;
import java.util.List;



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
