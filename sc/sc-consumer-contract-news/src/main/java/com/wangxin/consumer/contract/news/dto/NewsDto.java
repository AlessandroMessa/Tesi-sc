package com.wangxin.consumer.contract.news.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class NewsDto {
    private String id;
    private String title;
    private String description;
    private String address;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date newsTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date createTime;
}
