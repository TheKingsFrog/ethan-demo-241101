package com.ethan.ethandemo241101.mythread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "hello world";
    }
}
