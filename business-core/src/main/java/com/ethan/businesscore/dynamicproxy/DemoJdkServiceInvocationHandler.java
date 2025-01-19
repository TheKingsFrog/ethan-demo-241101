package com.ethan.businesscore.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DemoJdkServiceInvocationHandler implements InvocationHandler {

    public final Object target;

    public DemoJdkServiceInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("Before...");

        Object invoke = method.invoke(target, args);

        System.out.println("After...");

        return invoke;

    }

    public static void main(String[] args) {

        DemoJdkProxyService demoJdkProxyService = new DemoJdkProxyServiceImpl();

        DemoJdkProxyService proxy = (DemoJdkProxyService) Proxy.newProxyInstance(
                demoJdkProxyService.getClass().getClassLoader(),
                demoJdkProxyService.getClass().getInterfaces(),
                new DemoJdkServiceInvocationHandler(demoJdkProxyService)
        );

        proxy.process();

    }

}
