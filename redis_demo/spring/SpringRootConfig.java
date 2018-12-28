package com.nf.redisDemo1.spring;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.nf.redisDemo1.dao")
@Import({SpringDaoConfig.class, SpringServiceConfig.class})
@EnableCaching
public class SpringRootConfig {

}
