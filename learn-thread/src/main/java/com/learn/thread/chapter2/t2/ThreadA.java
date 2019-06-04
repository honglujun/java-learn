package com.learn.thread.chapter2.t2;

/**
 * @Title:
 * @Package
 * @Description
 * @Author 111665
 * @CreateDate 2018/09/29/14:36
 * @Version 1.0
 */
public class ThreadA extends Thread {

    private HasSelfPrivateNum numRef;

    public ThreadA(HasSelfPrivateNum numRef) {
        super();
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.addI("a");
    }

}
