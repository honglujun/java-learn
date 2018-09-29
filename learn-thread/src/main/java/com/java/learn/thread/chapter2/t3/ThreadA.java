package com.java.learn.thread.chapter2.t3;

/**
 * @Title:
 * @Package
 * @Description
 * @Author 111665
 * @CreateDate 2018/09/29/15:25
 * @Version 1.0
 */
public class ThreadA implements Runnable {

    private HasSelfPrivateNum numRef;

    public ThreadA(HasSelfPrivateNum numRef) {
        super();
        this.numRef = numRef;
    }

    @Override
    public void run() {
        numRef.addI("a");
    }
}
