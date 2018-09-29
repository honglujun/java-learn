package com.java.learn.thread.chapter2.t4;

/**
 * @Title:
 * @Package
 * @Description
 * @Author 111665
 * @CreateDate 2018/09/29/16:44
 * @Version 1.0
 */
public class Run {

    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        ThreadA threadA = new ThreadA(myObject);
        threadA.setName("A");
        ThreadB threadB = new ThreadB(myObject);
        threadB.setName("B");
        threadB.start();
        threadA.start();
    }


}
