package com.zjiajun.firstapp.model;

import java.io.Serializable;

/**
 * Created by zhujiajun
 * 15/8/2 15:29
 */
public class PersonSerialiable implements Serializable {

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonSerialiable{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
