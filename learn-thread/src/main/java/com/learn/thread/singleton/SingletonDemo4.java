package com.learn.thread.singleton;

/**
 * @Title:
 * @Package
 * @Description 饿汉模式（变种）
 * @Author 111665
 * @CreateDate 2018/09/26/10:55
 * @Version 1.0
 */
public class SingletonDemo4 {

    private SingletonDemo4() {
    }

    private static SingletonDemo4 instance;

    static {
        instance = new SingletonDemo4();
    }

    public static SingletonDemo4 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        SingletonDemo4.getInstance();
    }
}
