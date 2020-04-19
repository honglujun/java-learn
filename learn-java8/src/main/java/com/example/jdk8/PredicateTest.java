package com.example.jdk8;


import java.util.function.Predicate;

public class PredicateTest {

    public static void main(String[] args) {

        // Predicate的test抽象方法，接口接受一个入参，返回一个布尔值
        Predicate<String> predicate = p -> p.length() > 5;

        System.out.println(predicate.test("hello1234212"));
    }
}
