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
        Callee1 c1 = new Callee1();
        Callee2 c2 = new Callee2();
        MyIncrement.f(c2);
        Caller caller1 = new Caller(c1);
        Caller caller2 = new Caller(c2.getCallbackReference());
        caller1.go();
        caller1.go();
        caller2.go();
        caller2.go();
    }
}
/*
Output:
Other operation
1
1
2
Other operation
2
Other operation
3
 */
