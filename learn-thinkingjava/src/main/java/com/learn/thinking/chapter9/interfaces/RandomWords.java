package com.learn.thinking.chapter9.interfaces;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

/**
 * jdk中Readable接口是单独为Scanner类创建的，
 * Scanner类的构造器接受的是一个Readable接口
 *
 * 使得Scanner类不必将其参数限定为某个特定的类
 *
 * 换句话说：就是Scanner类可以接收实现了Readable接口的任何类
 *
 * @author win10
 */
public class RandomWords implements Readable {
    private static Random rand = new Random(47);
    private static final char[] capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] lowers = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] vowers = "aeiou".toCharArray();
    private int count;

    public RandomWords(int count) {
        this.count = count;
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        if (count-- == 0) {
            return -1;
        }
        cb.append(capitals[rand.nextInt(capitals.length)]);

        for (int i = 0; i < 4; i++) {
            cb.append(lowers[rand.nextInt(lowers.length)]);
            cb.append(vowers[rand.nextInt(vowers.length)]);
        }
        cb.append(" ");
        // Number of characters appended
        return 10;
    }

    public static void main(String[] args) {
        // 就是Scanner类可以接收实现了Readable接口的任何类
        Scanner s = new Scanner(new RandomWords(10));
        while (s.hasNext()) {
            System.out.println(s.next());
        }
    }
}
/*
Output:
Ynobenogi
Foozutoqe
Gsegemuje
Roisueeue
Neueolome
Hlieehoca
Reeuebiki
Naieobuwa
Kjirokope
Wsupodoco
 */