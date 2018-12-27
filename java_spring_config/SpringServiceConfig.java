package com.nf.redisDemo1.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.nf.redisDemo1.service")
public class SpringServiceConfig {
}
