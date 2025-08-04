package com.wangxin.consumer.service.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDto {
    private String   id;
    private String username;
    private String passwordHash;
    private String saltHex;
    private String trueName;
}
