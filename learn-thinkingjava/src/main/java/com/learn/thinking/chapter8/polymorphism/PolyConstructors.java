package com.learn.thinking.chapter8.polymorphism;

class Glyph {
    void draw() {
        System.out.println("Glyph.draw()");
    }

    Glyph() {
        System.out.println("Glyph() before draw()");
        draw();
        System.out.println("Glyph() after draw()");
    }
}

class RoundGlyph extends Glyph {
    private int radius = 1;

    RoundGlyph(int r) {
        radius = r;
        System.out.println("RoundGlyph.RoundGlyph().radius = " + radius);
    }

    @Override
    void draw() {
        System.out.println("RoundGlyph.draw().radius = " + radius);
    }
}

/**
 * @author win10
 */
public class PolyConstructors {

    public static void main(String[] args) {
        new RoundGlyph(5);
    }
}
/*
调用基类的构造器时，会调用覆盖后的draw()方法（在调用RoundGlyph构造器之前调用）
Output:
Glyph() before draw()
RoundGlyph.draw().radius = 0
Glyph() after draw()
RoundGlyph.RoundGlyph().radius = 5
 */