package com.learn.thinking.chapter10.innerclasses;

/**
 * 带实例初始化的“parcel”形式
 * <p>
 * 注意：destination() 的参数在jdk1.8之前必须要有final，
 * jdk1.8之后可以不需要，但是也是不能更改的
 *
 * 匿名内部类既可以扩展类，也可以实现接口，但是不能两者兼得，
 * 实现接口也只能实现一个接口
 */
public class Parcel10 {
    public Destination destination(final String dest, final float price) {
        // 实现接口
        return new Destination() {
            private int cost;

            // Instance initialization for each object:--实例初始化
            {
                cost = Math.round(price);
                if (cost > 100) {
                    System.out.println("Over budget!");
                }
            }

            private String label = dest;

            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        Parcel10 p = new Parcel10();
        Destination d = p.destination("Tasmania", 101.395F);
    }

}
/*
Output:
Over budget!
 */
