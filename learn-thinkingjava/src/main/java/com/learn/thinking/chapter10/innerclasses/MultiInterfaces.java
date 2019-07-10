package com.learn.thinking.chapter10.innerclasses;

interface A {}
interface B {}
class X implements A,B{}
class Y implements A{
    B makeB(){
        // Anonymous inner class--匿名内部类
        return new B(){};
    }
}
public class MultiInterfaces {
    static void takesA(A a) {}
    static void takesB(B b) {}

    public static void main(String[] args) {
        X x = new X();
        Y y = new Y();
        // X实现了A,B接口,所以不论是takesA()还是takesB()方法都是可以直接传递X对象的
        takesA(x);
        takesB(x);
        // Y只实现了A接口,所以takesA()可以直接传递Y对象，而takesB()要调用makeB()方法创建B对象来传递
        takesA(y);
        takesB(y.makeB());
    }
}
