package com.java.learn.thread.lock;

import java.util.concurrent.locks.Lock;

/**
 * @author Acer
 * @date
 */
public class ConsumerLock implements Runnable {

    private Lock lock;

    public ConsumerLock(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        int count = 100;
        while (count > 0) {
            try {
                lock.lock();
                count--;
                System.out.print("B");
            } finally {
                //主动释放锁
                lock.unlock();
                try {
                    Thread.sleep(91L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
