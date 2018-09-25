package com.java.learn.thread.waitandnotify;

/**
 * @author Acer
 * @date
 */
public class ConsumerSynchronized implements Runnable {

    @Override
    public void run() {

        int count = 100;
        while (count > 0) {
            synchronized (TestSynchronized.obj) {
                System.out.print("B");
                count--;
                // 主动释放对象锁
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
