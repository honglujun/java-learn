package com.learn.thread.singleton;

/**
 * @Title:
 * @Package
 * @Description 静态内部类(线程安全)
 * @Author 111665
 * @CreateDate 2018/09/26/10:30
 * @Version 1.0
 */
public class SingletonDemo5 {

    /**
     * 私有化构造方法
     */
    private SingletonDemo5() {
        System.out.println("SingletonDemo5 被创建");
    }

    private static class SingletonDemoHolder {
        public static final SingletonDemo5 instance = new SingletonDemo5();
    }

    public static final SingletonDemo5 getInstance() {
        return SingletonDemoHolder.instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            System.out.println("第"+i+"次调用");
            SingletonDemo5.getInstance();
        }
    }
}
