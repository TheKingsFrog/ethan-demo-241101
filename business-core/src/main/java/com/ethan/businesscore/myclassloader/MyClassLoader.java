package com.ethan.businesscore.myclassloader;

public class MyClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("my load class");
        return super.loadClass(name);
    }
}
