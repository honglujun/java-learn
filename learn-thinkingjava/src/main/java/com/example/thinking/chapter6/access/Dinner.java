package com.example.thinking.chapter6.access;//: access/Dinner.java
// Uses the library.

import com.example.thinking.chapter6.access.dessert.Cookie;

public class Dinner {
    public static void main(String[] args) {
        Cookie x = new Cookie();
        //! x.bite(); // Can't access
    }
} /* Output:
Cookie constructor
*///:~
