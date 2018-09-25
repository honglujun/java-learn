package com.java.learn.thread.lock;

import java.util.concurrent.locks.Lock;

/**
 * @Description:
 * @author: Acer
 * @Date: 2018-09-21 12:52
 */
public class ProducterLock implements Runnable {
    private Lock lock;

    public ProducterLock(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        int count = 100;
        while (count > 0) {
            try {
                lock.lock();
                count--;
                System.out.print("A");
            } finally {
                // 释放锁
                lock.unlock();
                try {
                    Thread.sleep(90L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
