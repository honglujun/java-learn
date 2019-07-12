package com.learn.thinking.chapter10.innerclasses.controller;

/**
 * 当希望运行Event并随后调用start()时，构造器就会捕获（从对象创建的时刻开始的）时间，
 * 此时间是这样来的：
 */
public abstract class Event {
    /**
     * 事件发生时间
     */
    private long eventTime;
    /**
     * 延迟时间
     */
    protected final long delayTime;

    public Event(long delayTime) {
        this.delayTime = delayTime;
        start();
    }

    /**
     * start()获取事件发生时间，该方法是一个独立的方法，
     * 因为这样可以在事件运行以后重新启动计时器，
     * 也就是能够重复使用Event对象，
     * 例如：如果想要重复一个事件，只需简单地在action()方法中调用start()方法。
     */
    public void start() {
        // Allows restarting
        eventTime = System.nanoTime() + delayTime;
    }

    /**
     * ready()告诉你何时运行action()方法---这个是在Controller类中实现的
     *
     * 当然，可以在导出类中覆盖ready()方法，使得Event能够基于时间之外的其他因素触发

     * @return
     */
    public boolean ready() {
        return System.nanoTime() >= eventTime;
    }

    /**
     * 具体的action()的实现，是通过继承本类，重写action()方法来提供的
     */
    public abstract void action();
}
