package com.ethan.ethandemo241101.functionalinterface;

import java.lang.reflect.Method;

@FunctionalInterface
public interface TestFunctionalInterface {

    void test();

    //    如果在接口上不用FunctionalInterface注解修饰，编译器也会自动识别为functionalInterface
    //    void test2(); 加这行编译器就报错了

    /**
     * 1.函数式接口允许有实现的方法，即default
     */
    default void test1(){
        System.out.println("test1");
        Method[] methods = this.getClass().getMethods();
    }

    /**
     * 2.函数式接口允许有Object同名方法
     * @param obj
     * @return
     */
     boolean equals(Object obj);


}
