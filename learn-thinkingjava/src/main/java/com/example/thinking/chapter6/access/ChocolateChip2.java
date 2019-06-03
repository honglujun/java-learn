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
//        chomp(); // 这种访问形式就是不行，静态的方法访问非静态的方法，应该通过类应用的形式
    }
}
/*Output:
Cookie constructor
Chocolate constructor
bite
 */