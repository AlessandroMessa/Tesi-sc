package com.wangxin.consumer.service.news;

import com.wangxin.common.api.model.simple.News;
import com.github.pagehelper.PageInfo;
import com.wangxin.consumer.service.news.dto.NewsDto;

public interface NewsService {
    NewsDto getNews();
    boolean addNews(NewsDto news);
    NewsDto findNewsById(String id);
    boolean editNews(NewsDto news);
    PageInfo<NewsDto> findNewsByPage(String keywords, Integer pageNum);
}
