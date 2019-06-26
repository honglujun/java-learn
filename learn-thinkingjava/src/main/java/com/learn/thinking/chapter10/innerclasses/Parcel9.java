package com.learn.thinking.chapter10.innerclasses;

/**
 * 在jdk8之前，如果定义一个匿名内部类，并且希望它使用一个在其外部定义的对象，
 * 编译器会要求参数应用是final的
 *
 * 在jdk8中可以不用final
 */
public class Parcel9 {
    public Destination destination(final String dest) {
        return new Destination() {
            private String label = dest;

            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        Parcel9 p = new Parcel9();
        Destination d = p.destination("Tasmania");
        System.out.println(d.readLabel());
    }
}
/*
Output:
Tasmania
 */