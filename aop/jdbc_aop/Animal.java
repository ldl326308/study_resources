package com.nf.lc.aop.jdbc_aop;

import org.springframework.stereotype.Component;

@Component
public class Animal {

    private String name;

    private int age;

    private String describe;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
