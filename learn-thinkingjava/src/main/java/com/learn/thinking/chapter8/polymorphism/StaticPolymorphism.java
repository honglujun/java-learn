package com.learn.thinking.chapter8.polymorphism;

class StaticSuper {
    public static String staticGet() {
        return "Base staticGet()";
    }

    public String dynamicGet() {
        return "Base dynamicGet()";
    }
}

class StaticSub extends StaticSuper {
    public static String staticGet() {
        return "Derived staticGet()";
    }

    @Override
    public String dynamicGet() {
        return "Derived dynamicGet()";
    }
}

/**
 * @author win10
 */
public class StaticPolymorphism {
    public static void main(String[] args) {
        // 向上转型
        StaticSuper sup = new StaticSub();
        // 静态方法不具有多态性，
        // 而且静态方法访问直接用类名访问就可以了：StaticSuper.staticGet()
        System.out.println(sup.staticGet());
        System.out.println(sup.dynamicGet());
    }
}
/*
静态方法是不具有多态性的
Output:
Base staticGet()
Derived dynamicGet()
 */