package com.learn.thread.waitandnotify;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Description:
 * @author: Acer
 * @Date: 2018-09-21 12:49
 */
public class TestSynchronized {

    public static final Object obj = new Object();

    public static void main(String[] args) {

        // 线程池创建线程
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(2, 4,
                3L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.execute(new ProducerSynchronized());
        singleThreadPool.execute(new ConsumerSynchronized());
        singleThreadPool.shutdown();

        // 显示创建线程
//        new Thread( new ProducerSynchronized()).start();
//        new Thread( new ConsumerSynchronized()).start();

    }
}
