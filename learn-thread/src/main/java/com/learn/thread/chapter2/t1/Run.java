package com.learn.thread.chapter2.t1;

/**
 * @Title:
 * @Package
 * @Description
 * @Author 111665
 * @CreateDate 2018/09/29/14:07
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        HasSelfPrivateNum numRef = new HasSelfPrivateNum();
        ThreadA threada = new ThreadA(numRef);
        threada.start();
        ThreadB threadb = new ThreadB(numRef);
        threadb.start();
    }

}
