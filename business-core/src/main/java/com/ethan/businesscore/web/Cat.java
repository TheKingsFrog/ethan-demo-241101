package com.ethan.businesscore.web;

public class Cat {

    private String name = "招财猫";

    public void hi() {
//        System.out.println("hi" + name);
    }

    public void cry(String name) {
        System.out.println("cry 喵喵叫" + name);
    }

    public void cry() {
        System.out.println("cry 喵喵叫" + name);
    }

}
