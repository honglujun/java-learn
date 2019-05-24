package com.example.thinking.chapter6.access;

class Sundae {
    private Sundae() {
    }

    static Sundae makeASundae() {
        return new Sundae();
    }
}

/**
 * @author win10
 */
public class IceCream {
    public static void main(String[] args) {
//        Sundae x = new Sundae(); // 不让new
        Sundae x = Sundae.makeASundae();
    }
}
