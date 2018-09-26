package com.java.learn.thread.singleton;

/**
 * @Title:
 * @Package
 * @Description 懒汉模式（线程不安全）
 * @Author 111665
 * @CreateDate 2018/09/25/17:09
 * @Version 1.0
 */
public class SingletonDemo1 {

    private static SingletonDemo1 instance;

    /**
     * 私有化构造方法
     */
    private SingletonDemo1(){

    }

    public static SingletonDemo1 getInstance(){
        if(instance == null){
            instance = new SingletonDemo1();
        }
        return instance;
    }


}
