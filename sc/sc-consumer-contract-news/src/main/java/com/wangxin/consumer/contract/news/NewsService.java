package com.wangxin.consumer.contract.news;


import com.github.pagehelper.PageInfo;
import com.wangxin.consumer.contract.news.dto.NewsDto;


public interface NewsService {
    NewsDto getNews();
    boolean addNews(NewsDto news);
    NewsDto findNewsById(String id);
    boolean editNews(NewsDto news);
    PageInfo<NewsDto> findNewsByPage(String keywords, Integer pageNum);
}
