package com.ethan.businesscore.dependencyreference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class B {

//    @Lazy
    private A a;

    public B() {}

    @Autowired
    public B(A a) {
        this.a = a;
        System.out.println("B constructor");
    }

    @Autowired
    public void setA(A a) {
        this.a = a;
        System.out.println("B got A");
    }

}
