package com.wangxin.consumer.service.news.impl;

import com.wangxin.consumer.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangxin.common.api.model.simple.News;
import com.github.pagehelper.PageInfo;
import com.wangxin.feign.web.remote.simple.NewsRemoteClient;

@Service
public class NewsRemoteService implements NewsService {

    @Autowired
    private  NewsRemoteClient newsRemoteClient;

    @Override
    public News getNews() {
        return newsRemoteClient.getNews();
    }

    @Override
    public boolean addNews(News news) {
        return newsRemoteClient.addNews(news);
    }

    @Override
    public News findNewsById(String id) {
        return newsRemoteClient.findNewsById(id);
    }

    @Override
    public boolean editNews(News news) {
        return newsRemoteClient.editNews(news);
    }

    @Override
    public PageInfo<News> findNewsByPage(String keywords, Integer pageNum) {
        return newsRemoteClient.findNewsByPage(keywords, pageNum);
    }
}
