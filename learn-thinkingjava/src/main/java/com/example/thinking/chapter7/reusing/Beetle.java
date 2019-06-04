package com.example.thinking.chapter7.reusing;

class Insect {
    private int i = 3;
    protected int j;

    Insect() {
        System.out.println("Insect constructor");
        System.out.println("i = " + i + ", j = " + j);
        j = 39;
    }

    static {
        System.out.println("static Insect 的静态代码块1");
    }

    private static int x1 = printInit("static Insect.x1 initialized");

    static {
        System.out.println("static Insect 的静态代码块2");
    }

    static int printInit(String s) {
        System.out.println(s);
        return 47;
    }
}

public class Beetle extends Insect {


    public Beetle() {
        System.out.println("Beetle constructor");
        System.out.println("k = " + k);
        System.out.println("j = " + j);
    }

    private int k = printInit("Beetle.k initialized");
    public static int x2 = printInit("static Beetle.x2 initialized");

    static {
        System.out.println("static Beetle 的静态代码块");
    }

    public static void main(String[] args) {
        System.out.println("Beetle.main() start");
        Beetle b = new Beetle();
    }
}
/*Output:
static Insect 的静态代码块1
static Insect.x1 initialized
static Insect 的静态代码块2
static Beetle.x2 initialized
static Beetle 的静态代码块
Beetle.main() start
Insect constructor
i = 3, j = 0
Beetle.k initialized
Beetle constructor
k = 47
j = 39
 */