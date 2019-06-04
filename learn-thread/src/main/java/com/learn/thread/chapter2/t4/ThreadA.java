package com.learn.thread.chapter2.t4;

/**
 * @Title:
 * @Package
 * @Description
 * @Author 111665
 * @CreateDate 2018/09/29/16:43
 * @Version 1.0
 */
public class ThreadA extends Thread {

    private MyObject myObject;

    public ThreadA(MyObject myObject) {
        super();
        this.myObject = myObject;
    }

    @Override
    public void run() {
        super.run();
        myObject.methodA();
    }
}
