package com.nf.redisDemo1;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nf.redisDemo1.entity.News;
import com.nf.redisDemo1.service.NewsService;
import com.nf.redisDemo1.service.imp.NewsServiceImp;
import com.nf.redisDemo1.spring.SpringRootConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        
        //注意：getBean()里面写接口
        NewsService bean = context.getBean(NewsService.class);

        List<News> newsList = bean.selectAllIsCacheable();
        System.out.println(newsList);


    }
}
