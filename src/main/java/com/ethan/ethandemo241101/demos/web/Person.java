package com.ethan.ethandemo241101.demos.web;

import lombok.Data;

@Data
public class Person {

    private static final String TEST_WORD = "hello world";

    public String name;
    public String gid;
    public int age;
    private String phone;

    private void setPhone(String phone) {
        this.phone = phone;
    }

    public Person(String name, String gid, Integer age) {
        this.name = name;
        this.gid = gid;
        this.age = age;
    }

    private Person(String name, String gid) {
        this.name = name;
        this.gid = gid;
    }

    private Person(String name) {
        this.name = name;
    }

    public Person(){}

}
