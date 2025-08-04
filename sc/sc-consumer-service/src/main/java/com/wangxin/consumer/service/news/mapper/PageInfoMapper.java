package com.wangxin.consumer.service.news.mapper;

import com.github.pagehelper.PageInfo;
import com.wangxin.common.api.model.simple.News;
import com.wangxin.consumer.service.news.dto.NewsDto;

import java.util.List;
import java.util.stream.Collectors;

public class PageInfoMapper {

    public static PageInfo<NewsDto> map(PageInfo<News> src) {
        if (src == null) return null;
        PageInfo<NewsDto> dst = new PageInfo<>();
        dst.setPageNum(src.getPageNum());
        dst.setPageSize(src.getPageSize());
        dst.setSize(src.getSize());
        dst.setStartRow(src.getStartRow());
        dst.setEndRow(src.getEndRow());
        dst.setTotal(src.getTotal());
        dst.setPages(src.getPages());
        List<NewsDto> dtoList = src.getList().stream()
                .map(NewsMapper::toDto)
                .collect(Collectors.toList());
        dst.setList(dtoList);
        dst.setPrePage(src.getPrePage());
        dst.setNextPage(src.getNextPage());
        dst.setFirstPage(src.getFirstPage());
        dst.setLastPage(src.getLastPage());
        dst.setIsFirstPage(src.isIsFirstPage());
        dst.setIsLastPage(src.isIsLastPage());
        dst.setHasPreviousPage(src.isHasPreviousPage());
        dst.setHasNextPage(src.isHasNextPage());
        return dst;
    }
}
