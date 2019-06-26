package com.learn.thinking.chapter10.innerclasses;

abstract class Base {
    public Base(int i) {
        System.out.println("Base constructor, i = " + i);
    }

    public abstract void f();
}

/**
 * Anonymous匿名的构造器
 *
 * 在匿名类中不可能有命名构造器，但是，
 * 通过实例初始化，就能够达到为匿名内部类创建一个构造器的效果
 */
public class AnonymousConstructor {
    public static Base getBase(int i) {
        return new Base(i) {
            {
                System.out.println("Inside instance initializer--在内部实例初始化");
            }

            @Override
            public void f() {
                System.out.println("In anonymous f()");
            }
        };
    }

    public static void main(String[] args) {
        Base base = getBase(47);
        base.f();
    }
}
/*
Output:
Base constructor, i = 47
Inside instance initializer--在内部实例初始化
In anonymous f()
 */