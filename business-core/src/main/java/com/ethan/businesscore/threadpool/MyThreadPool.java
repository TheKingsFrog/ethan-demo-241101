package com.ethan.businesscore.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MyThreadPool {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(16);

        executorService.shutdown();

        ThreadFactory threadFactory = Executors.defaultThreadFactory();

    }

}
