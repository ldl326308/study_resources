package com.nf.redisDemo1.dao;

import com.nf.redisDemo1.entity.News;

import java.util.List;


public interface NewsDAO {

    List<News> selectAll();

    void insert(News news);

    News selectByPrimaryKey(long id);

}
