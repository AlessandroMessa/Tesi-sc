package com.wangxin.consumer.contract.auth.dto;

import lombok.Data;

import java.util.List;
@Data
public class PermissionDto {
    private String url;
    private List<PermissionDto> children;
}
