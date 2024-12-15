package com.ethan.businesscore.lock;

import java.util.concurrent.atomic.AtomicBoolean;

public class MyDemoLock {

    private AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        int casCount = 0;
        // CAS机制，自旋等待，直到成功获取锁
        while (lock.compareAndSet(false, true)) {
            System.out.println(Thread.currentThread().getName() + "正在等待锁...");
        }
        System.out.println(Thread.currentThread().getName() + " 成功获取锁，经过自旋次数：" + casCount);
    }

    public void unlock() {
        lock.set(false);
    }

}
