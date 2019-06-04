package com.learn.thinking.chapter8.polymorphism.music;

class Stringed extends Instrument {
    @Override
    public void play(Note n) {
        System.out.println("Stringed.play() " + n);
    }
}

class Brass extends Instrument {
    @Override
    public void play(Note n) {
        System.out.println("Brass.play() " + n);
    }
}

/**
 * @author win10
 */
public class Music2 extends Instrument {


    public static void tune(Wind i) {
        i.play(Note.MIDDLE_C);
    }

    /**
     * Stringed：有弦(乐器)的；有卷须的
     *
     * @param i
     */
    public static void tune(Stringed i) {
        i.play(Note.MIDDLE_C);
    }

    /**
     * Brass:黄铜制品; (管弦乐团的) 铜管乐器，铜管乐器组;
     *
     * @param i
     */
    public static void tune(Brass i) {
        i.play(Note.MIDDLE_C);
    }
//    public static void tune(Instrument i){
//        i.play(Note.MIDDLE_C);
//    }

    public static void main(String[] args) {
        Wind flute = new Wind();
        Stringed violin = new Stringed();
        // frenchHorn：圆号
        Brass frenchHorn = new Brass();
        // 下面三个方法没有向上转型
        tune(flute);
        tune(violin);
        tune(frenchHorn);
    }
}
/*Output:
Wind.play() MIDDLE_C
Stringed.play() MIDDLE_C
Brass.play() MIDDLE_C
 */