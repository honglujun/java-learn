package com.example.thinking.chapter7.reusing;

import java.util.Random;

class Value {
    int i;

    public Value(int i) {
        this.i = i;
    }
}

public class FinalData {
    private static Random rand = new Random(47);
    private String id;

    public FinalData(String id) {
        this.id = id;
    }

    /**
     * can be compile-time constants 编译时常量
     */
    private final int valueOne = 9;
    private static final int VALUE_TWO = 99;
    /**
     * Typical public constants 典型的常量
     */
    private static final int VALUE_THREE = 39;
    /**
     * cannot be compile-time constants 非编译时常量
     */
    private final int i4 = rand.nextInt(20);
    static final int INT_5 = rand.nextInt(20);
    private Value v1 = new Value(11);
    private final Value v2 = new Value(22);
    private static final Value VAL_3 = new Value(33);
    private final int[] a = {1, 2, 3, 4, 5, 6};

    @Override
    public String toString() {
        return id + ": " + "i4 = " + i4 + ", INT_5 = " + INT_5;
    }

    public static void main(String[] args) {
        FinalData fd1 = new FinalData("fd1");
        fd1.v2.i++;// Object isn't constants
        fd1.v1 = new Value(9);
        for (int i = 0; i <fd1.a.length ; i++) {
            fd1.a[i]++; // Object isn't constants
//            fd1.v2 = new Value(0);// Error : Can't
//            fd1.VAL_3 = new Value(1);// change reference
//            fd1.a = new int[2];
        }
        System.out.println(fd1);
        System.out.println("Creating new FinalData");
        FinalData fd2 = new FinalData("fd2");
        System.out.println(fd1);
        System.out.println(fd2);
    }


}
/*Output:
fd1: i4 = 15, INT_5 = 18
Creating new FinalData
fd1: i4 = 15, INT_5 = 18
fd2: i4 = 13, INT_5 = 18
 */