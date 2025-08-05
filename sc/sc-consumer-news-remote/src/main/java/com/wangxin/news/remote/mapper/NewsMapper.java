package com.wangxin.news.remote.mapper;

import com.wangxin.common.api.model.simple.News;
import com.wangxin.consumer.contract.news.dto.NewsDto;


public class NewsMapper {

    public static NewsDto toDto(News src) {
        if (src == null) return null;
        NewsDto dto = new NewsDto();
        dto.setId(src.getId());
        dto.setTitle(src.getTitle());
        dto.setDescription(src.getDescription());
        dto.setAddress(src.getAddress());
        dto.setNewsTime(src.getNewsTime());
        dto.setCreateTime(src.getCreateTime());
        return dto;
    }

    public static News toRemote(NewsDto dto) {
        if (dto == null) return null;
        News news = new News();
        news.setId(dto.getId());
        news.setTitle(dto.getTitle());
        news.setDescription(dto.getDescription());
        news.setAddress(dto.getAddress());
        news.setNewsTime(dto.getNewsTime());
        news.setCreateTime(dto.getCreateTime());
        return news;
    }
}
