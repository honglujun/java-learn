package com.learn.thread.lock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @author: Acer
 * @Date: 2018-09-21 12:49
 */
public class TestLock {

    public static final Object obj = new Object();

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        ConsumerLock consumerLock = new ConsumerLock(lock);
        ProducerLock producerLock = new ProducerLock(lock);

        // 线程池创建线程
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(2, 4,
                3L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.execute(consumerLock);
        singleThreadPool.execute(producerLock);

        singleThreadPool.shutdown();

        // 显示创建线程
//        new Thread(consumerLock).start();
//        new Thread(producerLock).start();

    }
}
