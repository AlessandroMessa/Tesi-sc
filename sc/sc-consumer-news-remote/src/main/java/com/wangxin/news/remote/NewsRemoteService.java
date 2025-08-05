package com.wangxin.news.remote;


import com.github.pagehelper.PageInfo;
import com.wangxin.consumer.contract.news.NewsService;
import com.wangxin.consumer.contract.news.dto.NewsDto;
import com.wangxin.news.remote.mapper.NewsMapper;
import com.wangxin.feign.web.remote.simple.NewsRemoteClient;
import com.wangxin.news.remote.mapper.PageInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NewsRemoteService implements NewsService {

    @Autowired
    private NewsRemoteClient newsRemoteClient;

    @Override
    public NewsDto getNews() {
        return NewsMapper.toDto(newsRemoteClient.getNews());
    }

    @Override
    public boolean addNews(NewsDto news) {
        return newsRemoteClient.addNews(NewsMapper.toRemote(news));
    }

    @Override
    public NewsDto findNewsById(String id) {
        return NewsMapper.toDto(newsRemoteClient.findNewsById(id));
    }

    @Override
    public boolean editNews(NewsDto news) {
        return newsRemoteClient.editNews(NewsMapper.toRemote(news));
    }

    @Override
    public PageInfo<NewsDto> findNewsByPage(String keywords, Integer pageNum) {
        return PageInfoMapper.map(newsRemoteClient.findNewsByPage(keywords, pageNum));
    }
}
