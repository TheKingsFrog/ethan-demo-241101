package com.ethan.businesscore.dependencyreference;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class B {

    @Lazy
    private A a;

    public B() {
        System.out.println("B constructor");
    }

    public void setA(A a) {
        this.a = a;
        System.out.println("B got A");
    }

}
