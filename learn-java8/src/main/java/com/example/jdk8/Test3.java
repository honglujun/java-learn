package com.example.jdk8;


public class Test3 {
    public static void main(String[] args) {
        /**
         * 在将函数作为一等公民的语言中，Lambda表达式的类型是函数.
         * 但在java中，Lambda表达式是对象，
         * 他们必须依附于一类特别的的对象类型——函数式接口（functional interface）
         */
        MyInterface1 m1 = () -> {
        };
        System.out.println(m1.getClass().getInterfaces()[0]);
        MyInterface2 m2 = () -> {
        };
        System.out.println(m2.getClass().getInterfaces()[0]);

        // Runnable接口是函数式接口，创建线程
        new Thread(() -> {
            System.out.println("hello world!");
        }).start();

    }
}

@FunctionalInterface
interface MyInterface1 {
    void method1();
}

@FunctionalInterface
interface MyInterface2 {
    void method2();
}

/*
interface com.example.jdk8.MyInterface1
interface com.example.jdk8.MyInterface2
 */