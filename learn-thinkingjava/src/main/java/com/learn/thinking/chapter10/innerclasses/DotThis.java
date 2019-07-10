package com.learn.thinking.chapter10.innerclasses;

/**
 * 使用.this关键字
 */
public class DotThis {
    void f() {
        System.out.println("DotThis.f()");
    }

    public class Inner {
        public DotThis outer() {
            // 生成对外部类对象的引用，使用外部类的名字加.this
            return DotThis.this;
            // A plain "this" would be Inner's "this"
        }
    }

    public Inner inner() {
        System.out.println("输出");
        return new Inner();
    }

    public static void main(String[] args) {
        DotThis dt = new DotThis();
        DotThis.Inner dti = dt.inner();
        dti.outer().f();
        dti.outer().inner();
    }
}
/*
Output:
输出
DotThis.f()
输出
 */