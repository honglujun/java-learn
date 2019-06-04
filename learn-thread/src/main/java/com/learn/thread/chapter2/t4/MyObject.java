package com.learn.thread.chapter2.t4;

/**
 * @Title:
 * @Package
 * @Description 打印线程的名称
 * @Author 111665
 * @CreateDate 2018/09/29/16:38
 * @Version 1.0
 */
public class MyObject {

    public synchronized void methodA() {
        try {
            System.out.println("开始 methodA threadName = " + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()+"结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
