package com.example.thinking.chapter5;

class Window {
    Window(int marker) {
        System.out.println("Window(" + marker + ")");
    }
}

class House {
    Window w1 = new Window(1);
    House(){
        System.out.println("House");
        // w3再次被初始化，首次的初始化的引用对象会被丢弃
        w3 = new Window(33);
    }

    Window w2 = new Window(2);
    void f(){
        System.out.println("f()");
    }
    // w3首次初始化
    Window w3 = new Window(3);
}

/**
 * 构造器初始化
 *
 * @author win10
 */
public class OrderOfInitialization {
    public static void main(String[] args) {
        House h = new House();
        h.f();
    }
    /**
     * Output:
     * Window(1)
     * Window(2)
     * Window(3)
     * House
     * Window(33)
     * f()
     */
}
