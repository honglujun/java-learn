package com.example.thinking.chapter5.initialization;

class Bowl {
    Bowl(int marker) {
        System.out.println("Bowl(" + marker + ")");
    }

    void f1(int marker) {
        System.out.println("f1(" + marker + ")");
    }
}

class Table {
    static Bowl bowl1 = new Bowl(1);

    Table() {
        System.out.println("Table()");
        bowl2.f1(1);
    }

    Bowl bowl7 = new Bowl(7);

    void f2(int marker) {
        System.out.println("f2(" + marker + ")");
    }

    static Bowl bowl2 = new Bowl(2);
}

class Cupboard {
    Cupboard() {
        bowl3.f1(9);
        System.out.println("Cupboard()");
        bowl4.f1(2);
    }

    Bowl bowl3 = new Bowl(3);
    static Bowl bowl4 = new Bowl(4);

    void f3(int marker) {
        System.out.println("f3(" + marker + ")");
    }

    static Bowl bowl5 = new Bowl(5);
}

/**
 * 静态数据的初始化
 *
 * @author win10
 */
public class StaticInitialization {

    public static void main(String[] args) {
        System.out.println("Creating new Cupboard() in main");
        new Cupboard();
        System.out.println("Creating new Cupboard() in main");
        new Cupboard();
        table.f2(1);
        cupboard.f3(1);
    }

    // 1.最先初始化
    static Table table = new Table();
    // 2.接着初始化
    static Cupboard cupboard = new Cupboard();

}
/*
初始化先静态的对象，再非静态的对象；
静态对象按代码的先后顺序执行；
变量的初始化按代码的的先后顺序执行；
构造方法在静态对象，变量初始化结束之后开始执行；
构造方法只有到被new的时候才执行；
Output:
Bowl(1)
Bowl(2)
Bowl(7)   // 变量的初始化按代码的的先后顺序执行
Table()
f1(1)
Bowl(4)
Bowl(5)
Bowl(3)
f1(9)
Cupboard()
f1(2)
Creating new Cupboard() in main
Bowl(3)
f1(9)
Cupboard()
f1(2)
Creating new Cupboard() in main
Bowl(3)
f1(9)
Cupboard()
f1(2)
f2(1)
f3(1)
 */