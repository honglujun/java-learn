package com.example.thinking.chapter3.operators;

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
/*Output:
i = 58
j = 55
i > jtrue
i < j false
i >= jtrue
i <= false
i ==j false
i !=j true
 */