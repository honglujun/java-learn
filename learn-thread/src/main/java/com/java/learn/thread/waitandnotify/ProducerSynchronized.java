package com.java.learn.thread.waitandnotify;

/**
 * @Description:
 * @author: Acer
 * @Date: 2018-09-21 12:52
 */
public class ProducerSynchronized implements Runnable {

    @Override
    public void run() {
        int count = 100;
        while (count > 0) {
            synchronized (TestSynchronized.obj) {

                // System.out.print("count = " + count) ;
                System.out.print("A");
                count--;
                TestSynchronized.obj.notify();

                try {
                    TestSynchronized.obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
