package com.java.learn.thread.chapter2.t2;

/**
 * @Title:
 * @Package
 * @Description
 * @Author 111665
 * @CreateDate 2018/09/29/14:38
 * @Version 1.0
 */
public class Run {

    public static void main(String[] args) {
        HasSelfPrivateNum numRef = new HasSelfPrivateNum();
        for (int i = 0;i<30;i++) {
            ThreadB threadB = new ThreadB(numRef);
            threadB.start();
            ThreadA threadA = new ThreadA(numRef);
            threadA.start();
        }

    }
}
