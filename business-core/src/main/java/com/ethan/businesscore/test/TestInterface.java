package com.ethan.businesscore.test;

public interface TestInterface {

    public static final int i = 1;

    public abstract void doSomething();

    default void test() {
        System.out.println("hello world");
    }

}
