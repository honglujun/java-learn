package com.learn.thinking.chapter3.operators;

public class EqualsMath {

    public static void main(String[] args) {
        Value value1 = new Value();
        Value value2 = new Value();
        value1.value = value2.value = 1;

        System.out.println("value1.equlas(value2) :" + value1.equals(value2));
    }
}
