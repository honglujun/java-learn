package com.java.learn.thread.singleton;

/**
 * @Title:
 * @Package
 * @Description 饿汉模式
 * @Author 111665
 * @CreateDate 2018/09/26/9:01
 * @Version 1.0
 */
public class SingletonDemo3 {

    /**
     * 私有化构造
     */
    private SingletonDemo3(){}

    private static SingletonDemo3 instance = new SingletonDemo3();
    public SingletonDemo3 getInstance(){
        return instance;
    }

}
