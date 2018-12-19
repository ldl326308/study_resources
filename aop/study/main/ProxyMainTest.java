package com.nf.lc.aop.study.main;

import com.nf.lc.aop.study.entity.animal.Cat;
import com.nf.lc.aop.study.spring.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProxyMainTest {

    public static void main(String[] args) {
        //初始化容器
        ApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig.class);

        //获得bean
        Cat cat = ioc.getBean(Cat.class);
        //调用方法
        cat.setName("小花猫");


        try {
            String result = cat.play();
            System.out.println("result ：" + result);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


    }

}
