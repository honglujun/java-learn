package com.java.learn.thread.singleton;

/**
 * @Title:
 * @Package
 * @Description 懒汉模式（双重校验锁）
 * @Author 111665
 * @CreateDate 2018/09/26/11:12
 * @Version 1.0
 */
public class SingletonDemo7 implements Runnable {

    private SingletonDemo7() {

    }

    private static SingletonDemo7 instance = null;

    public static SingletonDemo7 getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo7.class) {
                if (instance == null) {
                    instance = new SingletonDemo7();
                }
            }
        }
        return instance;
    }

    @Override
    public void run() {
        SingletonDemo7 s7 = SingletonDemo7.getInstance();
        System.out.println(s7);
    }

    public static void main(String[] args) {
        new Thread(new SingletonDemo7(), "线程1").start();
        new Thread(new SingletonDemo7(), "线程2").start();
    }
}
