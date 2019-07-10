package com.learn.thinking.chapter10.innerclasses;
class D {}
abstract class E {}
class Z extends D {
    /**
     * 使用内部类实习多重继承
     */
    E makeE(){
        return new E(){};
    }
}
public class MultiImplementation {
    static void takesD(D d) {}
    static void takesE(E e) {}

    public static void main(String[] args) {
        Z z = new Z();
        takesD(z);
        // 如果拥有的是抽象类或者是具体的类，而不是接口，那只能使用内部类才能实现多重继承
        takesE(z.makeE());
    }
}
