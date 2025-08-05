package com.wangxin.consumer.contract.auth.dto;

import lombok.Data;

@Data
public class UserDto {
    private String   id;
    private String username;
    private String passwordHash;
    private String saltHex;
    private String trueName;
}
