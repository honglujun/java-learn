package com.java.learn.thread.singleton;

/**
 * @Title:
 * @Package
 * @Description 懒汉模式（线程安全）
 * @Author 111665
 * @CreateDate 2018/09/25/17:15
 * @Version 1.0
 */
public class SingletonDemo2 implements Runnable {

    /**
     * 私有化构造
     */
    private SingletonDemo2() {
        System.out.println("SingletonDemo2 被线程创建");
    }

    private static SingletonDemo2 instance = null;

    public static synchronized SingletonDemo2 getInstance() {
        if (instance == null) {
            instance = new SingletonDemo2();
        }
        return instance;
    }

    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            SingletonDemo2.getInstance();
        }
        System.out.println(System.currentTimeMillis() - beginTime);
    }

    public static void main(String[] args) {

        for (int i = 0; i < 500; i++) {
            new Thread(new SingletonDemo2()).start();
        }

    }
}
