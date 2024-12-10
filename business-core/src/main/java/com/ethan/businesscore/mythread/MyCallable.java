package com.ethan.businesscore.mythread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "hello world";
    }
}
