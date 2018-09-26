package com.java.learn.thread.waitandnotify;

/**
 * @Title:
 * @Package
 * @Description 打印字母ABC
 * @Author 111665
 * @CreateDate 2018/09/26/14:23
 * @Version 1.0
 */
public class PrintLetter implements Runnable {

    private String name;
    private Object prev;
    private Object self;

    public PrintLetter(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.print(name);
                    count--;
                    self.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


class PrintLetterTest {
    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        PrintLetter pa = new PrintLetter("A", c, a);
        PrintLetter pb = new PrintLetter("B", a, b);
        PrintLetter pc = new PrintLetter("C", b, c);


        new Thread(pa).start();
        Thread.sleep(10);
        new Thread(pb).start();
        Thread.sleep(10);
        new Thread(pc).start();
        Thread.sleep(10);
    }
}

