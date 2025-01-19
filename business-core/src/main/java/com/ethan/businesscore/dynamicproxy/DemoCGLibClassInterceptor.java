package com.ethan.businesscore.dynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DemoCGLibClassInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("Before...");

        Object object = methodProxy.invokeSuper(o, objects);

        System.out.println("After...");

        return object;

    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(DemoCGLibClass.class);
        enhancer.setCallback(new DemoCGLibClassInterceptor());
        DemoCGLibClass proxy = (DemoCGLibClass) enhancer.create();
        proxy.process();

    }

}
