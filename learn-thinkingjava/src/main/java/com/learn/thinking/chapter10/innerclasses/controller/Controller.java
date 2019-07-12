package com.learn.thinking.chapter10.innerclasses.controller;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Event> eventList = new ArrayList<>();

    public void addEvent(Event c) {
        eventList.add(c);
    }

    /**
     * run()方法循环遍历eventList，寻找就绪的（ready()）、要运行的Event对象。
     *
     * 启动main方法的时候会一直跑的原因不是别的，就是Restart内部类
     */
    public void run() {
        while (eventList.size() > 0) {
            for (Event e : new ArrayList<>(eventList)) {
                if (e.ready()) {
                    System.out.println(e);
                    e.action();
                    eventList.remove(e);
                }
            }
        }
    }
}
