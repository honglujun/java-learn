package com.java.learn.thread.chapter2.t3;

/**
 * @Title:
 * @Package
 * @Description
 * @Author 111665
 * @CreateDate 2018/09/29/15:25
 * @Version 1.0
 */
public class ThreadB extends Thread{
    private HasSelfPrivateNum numRef;
    public ThreadB (HasSelfPrivateNum numRef){
        super();
        this.numRef= numRef;
    }

    @Override
    public void run(){
        numRef.addI("b");
    }

}
