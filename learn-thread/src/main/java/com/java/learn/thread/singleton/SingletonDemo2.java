package com.java.learn.thread.singleton;

/**
 * @Title:
 * @Package
 * @Description 懒汉模式（线程安全）双重校验锁
 * @Author 111665
 * @CreateDate 2018/09/25/17:15
 * @Version 1.0
 */
public class SingletonDemo2 {

    private static SingletonDemo2 instance;

    /**
     * 私有化构造
     */
    private SingletonDemo2(){}

    public static SingletonDemo2 getInstance(){
        // 双重校验锁
        if(instance == null){
            synchronized (SingletonDemo2.class){
                if(instance == null){
                    instance = new SingletonDemo2();
                }
            }
        }
        return instance;
    }

}
