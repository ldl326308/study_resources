package com.nf.lc.aop.jdbc_aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        Animal animal = new Animal();
        animal.setName("小猎豹");
        animal.setAge(4);
        animal.setDescribe("这是一只跑步很快的豹子");

        //初始化容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        AnimalDAO bean = context.getBean(AnimalDAO.class);

        try {
            bean.save(animal);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
