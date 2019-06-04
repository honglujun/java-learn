package com.learn.thinking.chapter8.polymorphism.music;

/**
 * 向上转型
 *
 * @author win10
 */
public class Music {
    /**
     * 接受了一个基类（Instrument），但是传入的参数是导出类（Wind）
     * 向上转型
     *
     * Instrument：仪器，器具
     * @param i
     */
    public static void tune(Instrument i) {
        i.play(Note.MIDDLE_C);
    }

    public static void main(String[] args) {
        // 长笛
        Wind flute = new Wind();
        // 最后使用的是Wind.play()
        tune(flute);
    }
}
/*
调顶层（基类或者叫父类），用底层（导出类或者叫子类）

Output:
Wind.play() MIDDLE_C
 */