package com.learn.thinking.chapter9.interfaces;

interface I1 {
    void f();
}

interface I2 {
    int f(int i);
}

interface I3 {
    int f();
}

class C {
    public int f() {
        return 1;
    }
}

class C2 implements I1, I2 {

    @Override
    public void f() {

    }

    @Override
    public int f(int i) {
        return 1;
    }
}

class C3 extends C implements I2 {

    @Override
    public int f(int i) {
        return 1;
    }
}

class C4 extends C implements I3{
    @Override
    public int f(){return 1;}
}
// Methods differ only by return type:
//! class C5 extends C implements I1 {}
//! interface I4 extends I1, I3 {} ///:~

/**
 * 在不同的接口中不要使用相同的方法名，以防止代码可读性的混乱
 */
public class InterfaceCollision {
}
