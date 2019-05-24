package com.example.thinking.chapter6.access;

import com.example.thinking.chapter6.access.cookie2.Cookie;

/**
 * @author win10
 */
public class ChocolateChip2 extends Cookie {
    public ChocolateChip2() {
        System.out.println("Chocolate constructor");
    }

    public void chomp() {
        bite();//protected method
    }

    public static void main(String[] args) {
        ChocolateChip2 x = new ChocolateChip2();
        x.chomp();
    }
}
/*Output:
Cookie constructor
Chocolate constructor
bite
 */