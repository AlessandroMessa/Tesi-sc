package com.wangxin.consumer.service.news;

import com.wangxin.common.api.model.simple.News;
import com.github.pagehelper.PageInfo;

public interface NewsService {
    News getNews();
    boolean addNews(News news);
    News findNewsById(String id);
    boolean editNews(News news);
    PageInfo<News> findNewsByPage(String keywords, Integer pageNum);
}
