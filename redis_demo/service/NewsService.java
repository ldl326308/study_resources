package com.nf.redisDemo1.service;

import com.nf.redisDemo1.entity.News;

import java.io.IOException;
import java.util.List;

public interface NewsService {

    List<News> selectAll() throws IOException, ClassNotFoundException;

    List<News> selectAllByteToRedis() throws IOException, ClassNotFoundException;

    void insert(News news);

    News selectByPrimaryKey(long id);

   List<News> selectAllIsCacheable();

}
