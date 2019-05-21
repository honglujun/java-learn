package com.example.thinking.chapter5;

import java.util.Random;

public class Bool {
    public static void main(String[] args) {
        Random random = new Random(47);
        int i = random.nextInt(100);
        int j = random.nextInt(100);
        System.out.println("i = " + i);
        System.out.println("j = " + j);
        System.out.println("i > j" + (i > j));
        System.out.println("i < j " + (i < j));
        System.out.println("i >= j" + (i >= j));
        System.out.println("i <= " + (i <= j));
        System.out.println("i ==j " + (i == j));
        System.out.println("i !=j " + (i != j));

    }
}
