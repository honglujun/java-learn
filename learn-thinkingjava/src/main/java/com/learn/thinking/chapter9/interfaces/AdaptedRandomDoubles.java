package com.learn.thinking.chapter9.interfaces;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Scanner;

/**
 * 适配器模式
 * 被适配的类AdapterRandomDoubles通过继承RandomDoubles类和实现Readable接口，
 * 可以生成既是RandomDoubles又是Readable的新类
 *
 *
 * RandomDoubles类并没有实现Readable接口，
 * 但是通过AdapterRandomDoubles继承RandomDoubles类，实现Readable接口
 * 这样使得Scanner类也能作用于RandomDoubles类上
 */
public class AdaptedRandomDoubles extends RandomDoubles implements Readable {
    private int count;

    public AdaptedRandomDoubles(int count) {
        this.count = count;
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        if (count-- == 0) {
            return -1;
        }
        // 通过继承RandomDoubles类得到的next()方法
        String result = next() + " ";
        cb.append(result);
        return result.length();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(new AdaptedRandomDoubles(7));
        while (s.hasNextDouble()) {
            System.out.println(s.nextDouble() + " ");
        }
    }
}
/*
Output:
0.7271157860730044
0.5309454508634242
0.16020656493302599
0.18847866977771732
0.5166020801268457
0.2678662084200585
0.2613610344283964
 */