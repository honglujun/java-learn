package com.learn.thread.chapter2.t4;

/**
 * @Title:
 * @Package
 * @Description
 * @Author 111665
 * @CreateDate 2018/09/29/16:44
 * @Version 1.0
 */
public class ThreadB extends Thread {

    private MyObject myObject;

    public ThreadB(MyObject myObject) {
        super();
        this.myObject = myObject;
    }

    @Override
    public void run() {
        super.run();
        myObject.methodA();
    }
}
