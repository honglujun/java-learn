package com.learn.thread.classload;

/**
 * @Title:
 * @Package
 * @Description
 * @Author 111665
 * @CreateDate 2018/10/17/20:36
 * @Version 1.0
 */
public class HelloA {

    public HelloA() {
        System.out.println("HelloA");
    }

    Aclass ac = new Aclass() {
        @Override
        public Bclass func() {
            return super.func();
        }
    };

    {
        System.out.println("I'm A class");
    }

    static {
        System.out.println("static A");
    }

}
