package com.ethan.businesscore.dependencyreference;

import org.springframework.stereotype.Component;

@Component
public class A {

    // 解决循环依赖方式一：加lazy注解（不需要允许循环依赖）
//    @Lazy
    // 解决循环依赖方式三：自动注入成员变量（需要允许循环依赖）
    private B b;

    public A() {
        System.out.println("A constructure");
    }

    // 解决循环依赖方式二：自动注入setter方法（需要允许循环依赖）
    public void setB(B b) {
        this.b = b;
        System.out.println("A got B");
    }

}
