package com.ethan.ethandemo241101.mythread;

public class MyThread implements Runnable {

    @Override
    public void run() {

    }

    public static void main(String[] args) {

        new Thread(MyThread::new).start();

        // 方式1
        Thread hello = new Thread(() -> System.out.println("hello"));
        // 方式2
        Thread world = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("world");
            }
        });

    }

}
