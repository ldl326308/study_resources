package com.nf.lc.aop.study.entity.animal;

import org.springframework.stereotype.Component;

@Component
public class Cat {

    private String name;

    public String play() throws Exception {
        if (this.name.length() > 3) {
            throw new Exception("猫的名字长度大于3出现错误");
        }

        System.out.println(this.name + "在玩");

        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
