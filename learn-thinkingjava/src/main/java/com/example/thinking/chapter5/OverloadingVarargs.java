package com.example.thinking.chapter5;

/**
 * @author win10
 */
public class OverloadingVarargs {
    static void f(Character... args) {
        System.out.print("first");
        for (Character c : args) {
            System.out.print(" " + c);
        }
        System.out.println();
    }

    static void f(Integer... args) {
        System.out.print("second");
        for (Integer i : args) {
            System.out.print(" " + i);
        }
        System.out.println();
    }

    static void f(Long... args) {
        System.out.println("third");
    }

    public static void main(String[] args) {
        f('a', 'b', 'c');
        f(1);
        f(1, 2, 3);
        f(0);
        f(0L);
//        f(); // 编译报错
    }
}
/*Output:
first a b c
second 1
second 1 2 3
second 0
third
 */