package com.learn.thinking.chapter10.innerclasses;

/**
 * 匿名内部类
 */
public class Parcel7 {
    public Contents contents() {
        // 等价于下面的代码，这是java8 的新特性，函数式接口编程
//        return () -> 11;
        // Insert a class definition
        return new Contents() {
            private int i = 11;

            @Override
            public int value() {
                return i;
            }
        };
    }

    public static void main(String[] args) {
        Parcel7 p =new Parcel7();
        Contents contents = p.contents();
    }
}
