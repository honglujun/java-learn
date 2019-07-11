package com.learn.thinking.chapter10.innerclasses;

interface Incrementable {
    /**
     * 增加
     */
    void increment();
}

/**
 * 外围类实现接口
 */
class Callee1 implements Incrementable {
    private int i = 0;

    @Override
    public void increment() {
        i++;
        System.out.println(i);
    }
}

class MyIncrement {
    public void increment() {
        System.out.println("Other operation");
    }

    static void f(MyIncrement mi) {
        mi.increment();
    }
}

/**
 * If your class must implement increment() in
 * some other way, you must use an inner class:
 * ===========================================
 * 如果类必须以其他方式实现increment()，则必须使用内部类
 */
class Callee2 extends MyIncrement {
    private int i = 0;

    @Override
    public void increment() {
        super.increment();
        i++;
        System.out.println(i);
    }

    /**
     * 内部类实现接口
     * 内部类Closure实现了Incrementable，用来提供一个返回Callee2的“钩子”--而且是一个安全的钩子
     * 无论谁获得此Incrementable的引用，都只能调用increment()
     */
    private class Closure implements Incrementable {
        @Override
        public void increment() {
            // Specify outer-class method, otherwise you'd get an infinite recursion
            // 指定外部类方法，否则将得到无限递归
            Callee2.this.increment();
        }
    }

    Incrementable getCallbackReference() {
        return new Closure();
    }
}

/**
 * Caller的构造器需要一个Incrementable的引用作为参数（虽然可以在任意时刻捕获回调引用），
 * 然后在以后的某个时刻，Caller对象可以使用此引用回调Callee类。
 */
class Caller {
    private Incrementable callbackReference;

    Caller(Incrementable cbh) {
        callbackReference = cbh;
    }

    void go() {
        callbackReference.increment();
    }
}

/**
 * 这个例子进一步展示了外围类实现一个接口与内部类实现一个接口之间的区别
 */
public class Callbacks {
    public static void main(String[] args) {
        // Callee1直接实现了接口Incrementable，简单的解决方式
        Callee1 c1 = new Callee1();
        Caller caller1 = new Caller(c1);
        caller1.go();
        caller1.go();
        System.out.println("======================");

        /**
         * Callee2继承了MyIncrement,
         * MyIncrement中有了与接口中相同方法名的方法increment(),
         * MyIncrement的这个方法与接口的方法完全是不相关的。
         * 所以如果Callee2继承了MyIncrement，
         * 就不能为了Incrementable的用途覆盖increment()方法，
         * 只能用内部类的方式独立的实现Incrementable接口
         */
        Callee2 c2 = new Callee2();
        MyIncrement.f(c2);
        Caller caller2 = new Caller(c2.getCallbackReference());
        caller2.go();
        caller2.go();
    }
}
/*
Output:
1
2
======================
Other operation
1
Other operation
2
Other operation
3

 */
