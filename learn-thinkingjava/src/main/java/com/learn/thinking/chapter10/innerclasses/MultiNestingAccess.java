package com.learn.thinking.chapter10.innerclasses;

class MNA {
    private void f(){
        System.out.println("f()");
    }
    class A{
        private void g(){
            System.out.println("g()");
        }
        // 外围类访问内部类
        public void c(){
             A a = new A();
             B b = a.new B();
             b.h();
            System.out.println("b.h() 外围类访问内部类");
        }
        public class B {
            void h() {
                g();
                f();
            }
        }
    }
}
public class MultiNestingAccess {
    public static void main(String[] args) {
        MNA mna = new MNA();
        // 普通内部类的创建方式,关键字new
        MNA.A mnaa = mna.new A();
        mnaa.c();
        MNA.A.B mnaab = mnaa.new B();
        // 内部类可以直接访问外围类
        mnaab.h();
    }
}
/*
Output:
g()
f()
b.h() 外围类访问内部类
g()
f()
 */
