package com.learn.thinking.chapter10.innerclasses;

/**
 * 基类Wrapping中需要一个入参的匿名内部类
 */
public class Parcel8 {
    public Wrapping wrapping(int x) {
        return new Wrapping(x) {
            @Override
            public int value() {
                return super.value() * 47;
            }
        };// Semicolon required 需要分号
    }

    public static void main(String[] args) {
        Parcel8 p = new Parcel8();
        Wrapping w = p.wrapping(10);
        System.out.println(w.value()+" ");
    }
}
/*
Output:
470
 */