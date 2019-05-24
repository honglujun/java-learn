package com.example.thinking.chapter5.initialization;

import java.util.Arrays;
import java.util.Random;

/**
 * @author win10
 */
public class ArrayNew {
    public static void main(String[] args) {
        int[] a;
        Random rand = new Random(47);
        a = new int[rand.nextInt(20)];
        System.out.println("length.of a = " + a.length);
        System.out.println(Arrays.toString(a));
    }
}
/*Output:
length.of a = 18
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

数组大小是通过Random.nextInt()方法随机决定的
 */