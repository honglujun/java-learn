package com.learn.thinking.chapter8.polymorphism;

class Meal {
    Meal() {
        System.out.println("Meal() constructor");
    }
}

class Bread {
    Bread() {
        System.out.println("Bread() constructor");
    }
}

class Cheese {
    Cheese() {
        System.out.println("Cheese() constructor");
    }
}

class Lettuce {
    Lettuce() {
        System.out.println("Lettuce() constructor");
    }
}

class Lunch extends Meal {
    Lunch() {
        System.out.println("Lunch() constructor");
    }
}

class PortableLunch extends Lunch {
    PortableLunch() {
        System.out.println("PortableLunch() constructor");
    }
}

/**
 * @author win10
 */
public class Sandwich extends PortableLunch {

    private Cheese c = new Cheese();
    private Lettuce l = new Lettuce();

    public Sandwich() {
        System.out.println("Sandwich() constructor");
    }

    public static void main(String[] args) {
        new Sandwich();
    }

    /**
     * 有静态的成员，先初始化静态的成员
     */
    private static Bread b = new Bread();
}
/*
调用顺序：(有静态的成员，先初始化静态的成员)
1)调用基类构造器，这个过程会不断递归下去，首先是构造这种层次结构的根Meal，然后是下一层导出类Lunch，等等，直到最底层的导出类Sandwich
2)按声明顺序调用成员的初始化方法
3)调用导出类构造器主体
Output:
Bread() constructor  // 有静态的成员，先初始化静态的成员
Meal() constructor // 1)调用基类构造器
Lunch() constructor  // 1)调用基类构造器
PortableLunch() constructor // 1)调用基类构造器
Cheese() constructor // 2)按声明顺序调用成员的初始化方法
Lettuce() constructor // 2)按声明顺序调用成员的初始化方法
Sandwich() constructor // 3)调用导出类构造器主体
 */