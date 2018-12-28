package com.nf.redisDemo1.service.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nf.redisDemo1.dao.NewsDAO;
import com.nf.redisDemo1.entity.News;
import com.nf.redisDemo1.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.List;

@Service
public class NewsServiceImp implements NewsService{

    @Autowired
    private NewsDAO newsDAO;


    @Cacheable("list") //自动写入到Redis中
    @Override
    public List<News> selectAllIsCacheable(){
        return newsDAO.selectAll();
    }


    @Override
    public List<News> selectAllByteToRedis() throws IOException, ClassNotFoundException {
        Jedis jedis = new Jedis();
        String key = "listNews";

        ObjectMapper objectMapper = new ObjectMapper();

        if (jedis.exists(key.getBytes())) {  //存在则在Redis中获得
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(jedis.get(key.getBytes()));
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            List<News> o = (List<News>) objectInputStream.readObject();
            return o;
        }

        List<News> news = newsDAO.selectAll();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(news);

        jedis.set(key.getBytes(), baos.toByteArray());

        oos.close();

        return news;
    }



    @Override
    public List<News> selectAll() {
        List<News> news = newsDAO.selectAll();

        //数据写入 Redis
        setMultipleDataToRedis(news);

        return news;
    }

    /**
     * 写入单个 News 数据到Redis数据库中
     *
     * @param news
     */
    public void setSingleDataToRedis(News news) {
        Jedis jedis = new Jedis();
        //保存到 Redis
        // id title body
        // id 保存格式： newsId - id
        // title 保存格式： news:id:title - title
        //body 保存格式： news:id:body - body
        jedis.set("newsId" + news.getId(), String.valueOf(news.getId()));  //保存id
        jedis.set("news:" + news.getId() + ":title", news.getTitle());//保存标题
        jedis.set("news:" + news.getId() + ":body", news.getBody()); //保存内容

        jedis.close();

    }

    /**
     * 写入多个 News 数据到Redis数据库中
     *
     * @param news
     */
    public void setMultipleDataToRedis(List<News> news) {
        for (News item : news) {
            setSingleDataToRedis(item);
        }
    }


    @Override
    public void insert(News news) {
        newsDAO.insert(news);
        //添加到 Redis 数据库
        setSingleDataToRedis(news);
    }

    @Override
    public News selectByPrimaryKey(long id) {
        // Redis 中查询
        Jedis jedis = new Jedis();
        String dataId = jedis.get("newsId" + id);

        if (dataId != null) {  //数据存在Redis中，Redis得到
            News dataNews = new News();
            dataNews.setId(id);
            dataNews.setTitle(jedis.get("news:" + id + ":title"));
            dataNews.setBody(jedis.get("news:" + id + ":body"));
            return dataNews;
        }

        // Redis 中没有，Mariadb 数据库查询
        News news = newsDAO.selectByPrimaryKey(id);
        //写入 Redis 数据库
        setSingleDataToRedis(news);

        return news;
    }
}
