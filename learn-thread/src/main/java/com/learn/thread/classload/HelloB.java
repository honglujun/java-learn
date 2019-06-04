package com.learn.thread.classload;

/**
 * @Title:
 * @Package
 * @Description
 * @Author 111665
 * @CreateDate 2018/10/17/20:36
 * @Version 1.0
 */
public class HelloB extends HelloA {

    public HelloB() {
        System.out.println("HelloB");
    }

    {
        System.out.println("I'm B class");
    }

    static {
        System.out.println("static B");
    }

    public static void main(String[] args) {

        System.out.println("-------main start-------");
        new HelloB();
        new HelloB();
        System.out.println("-------main end-------");
    }
}
