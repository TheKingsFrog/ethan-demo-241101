package com.ethan.businesscore.hashcode;


public class MyHashCodeTest {

    private Integer age;

    private String name;

    public MyHashCodeTest() {

    }

    public MyHashCodeTest(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {

        MyHashCodeTest obj1 = (MyHashCodeTest) obj;

        return obj1.getName().equals(this.name);

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
