package com.learn.thinking.chapter5.initialization;

class Cup {
    Cup(int marker) {
        System.out.println("Cup(" + marker + ")");
    }

    void f(int marker) {
        System.out.println("f(" + marker + ")");
    }
}

class Cups {
    static Cup cup1;
    static Cup cup2;

    static {
        cup1 = new Cup(1);
        cup2 = new Cup(2);
    }

    Cups() {
        System.out.println("Cups()");
    }
}

/**
 * 显示的静态初始化
 *
 * @author win10
 */
public class ExplicitStatic {
    public static void main(String[] args) {
        System.out.println("Inside main()");
        Cups.cup1.f(99);// (1)
    }

//    static Cups cups1 = new Cups(); // (2)
//    static Cups cups2 = new Cups(); // (2)
}
/*Output:
Inside main()
Cup(1)
Cup(2)
f(99)


无论是通过(1)的代码代码访问静态的cup1对象，还是把标(1)的行注释掉，去执行标为(2)
的代码（即解除(2)的注释），Cups的静态初始化都会执行。如果把(1)(2)的注释都解除，
Cups的静态初始化就不会进行


 */

