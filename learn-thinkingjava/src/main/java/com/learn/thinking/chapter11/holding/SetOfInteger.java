package com.learn.thinking.chapter11.holding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SetOfInteger {
    public static void main(String[] args) {
        Random rand = new Random(47);
        Set<Integer> intSet = new HashSet<>();
        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            intSet.add(rand.nextInt(10));
            intList.add(rand.nextInt(10));
        }
        System.out.println("Set:" + intSet);
        System.out.println("List:" + intList);
    }
}
/*
Output:
Set:[0, 1, 2, 3, 4, 6, 8, 9]
List:[5, 1, 9, 0, 7, 8, 9, 8, 1, 8, 0, 2, 3, 5, 2, 4, 6, 6, 4, 0]
 */
