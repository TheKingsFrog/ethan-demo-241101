package com.ethan.ethandemo241101.demos.web;

import lombok.Data;

@Data
public class Person {

    public String name;
    public String gid;
    public int age;

    public Person(String name, String gid, Integer age) {
        this.name = name;
        this.gid = gid;
        this.age = age;
    }

    public Person(){}

}
