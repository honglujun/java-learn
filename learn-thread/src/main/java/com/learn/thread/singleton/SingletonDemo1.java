package com.learn.thread.singleton;

/**
 * @Title:
 * @Package
 * @Description 懒汉模式（线程不安全）
 * @Author 111665
 * @CreateDate 2018/09/25/17:09
 * @Version 1.0
 */
public class SingletonDemo1 implements Runnable {

    /**
     * 私有化构造方法
     */
    private SingletonDemo1() {
        System.out.println("SingletonDemo1 被线程"+Thread.currentThread().getName()+"创建");
    }

    private static SingletonDemo1 instance = null;

    public static SingletonDemo1 getInstance() {
        if (instance == null) {
            instance = new SingletonDemo1();
        }
        return instance;
    }


    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            SingletonDemo1.getInstance();
        }
        System.out.println(System.currentTimeMillis() - beginTime);
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
            new Thread(new SingletonDemo1()).start();
        }
    }
}
