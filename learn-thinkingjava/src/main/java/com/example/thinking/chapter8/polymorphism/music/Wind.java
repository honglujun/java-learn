package com.example.thinking.chapter8.polymorphism.music;

/**
 * @author win10
 */
public class Wind extends Instrument {
    /**
     * 重写Instrument.play()方法
     *
     * @param n
     */
    @Override
    public void play(Note n) {
        System.out.println("Wind.play() " + n);
    }

}
