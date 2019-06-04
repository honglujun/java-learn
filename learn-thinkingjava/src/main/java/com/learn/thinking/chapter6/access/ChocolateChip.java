package com.learn.thinking.chapter6.access;

import com.learn.thinking.chapter6.access.dessert.Cookie;

/**
 * @author win10
 */
public class ChocolateChip extends Cookie {
    public ChocolateChip() {
        System.out.println("ChocolateChip constructor");
    }

    public void chomp() {
//        bite();// Can't access bite
    }

    public static void main(String[] args) {
        ChocolateChip x = new ChocolateChip();
        x.chomp();
    }
}
/*Output:
Cookie constructor
ChocolateChip constructor
 */