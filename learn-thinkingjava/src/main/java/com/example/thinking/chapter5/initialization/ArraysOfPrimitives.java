package com.example.thinking.chapter5.initialization;

/**
 * 数组初始化
 *
 * @author win10
 */
public class ArraysOfPrimitives {
    public static void main(String[] args) {
        int[] a1 = {1, 2, 3, 4, 5};
        int[] a2;
        a2 = a1;
        for (int i = 0; i < a2.length; i++) {
            a2[i] = a2[i] + 1;
        }

        for (int i = 0; i < a1.length; i++) {
            System.out.println("a1[" + i + "] = " + a1[i]);
        }
        System.out.println();
        for (int i = 0; i < a2.length; i++) {
            System.out.println("a2[" + i + "] = " + a2[i]);
        }
    }
}
/*
a1[0] = 2
a1[1] = 3
a1[2] = 4
a1[3] = 5
a1[4] = 6

a2[0] = 2
a2[1] = 3
a2[2] = 4
a2[3] = 5
a2[4] = 6

其实真正做的只是复制了一个引用
 */
