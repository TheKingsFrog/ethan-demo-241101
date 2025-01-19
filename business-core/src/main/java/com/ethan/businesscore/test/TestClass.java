package com.ethan.businesscore.test;

public class TestClass extends TestAbstractClass {

    private static final String NAME = "name";

    public TestClass() {
    }

    public TestClass(String testName) {
        System.out.println("mamba out");
    }

    public static void main(String[] args) {
        TestClass test = new TestClass("Man!");
    }
}
