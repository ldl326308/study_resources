package com.nf.lc.aop.study.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.nf.lc.aop")
@EnableAspectJAutoProxy
public class SpringConfig {
}
