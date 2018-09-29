package com.java.learn.thread.chapter2.t3;

/**
 * @Title:
 * @Package
 * @Description
 * @Author 111665
 * @CreateDate 2018/09/29/15:25
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        HasSelfPrivateNum numRef = new HasSelfPrivateNum();
        ThreadA threadA = new ThreadA(numRef);
        new Thread(threadA).start();
        ThreadB threadB = new ThreadB(numRef);
        threadB.start();
    }

}
