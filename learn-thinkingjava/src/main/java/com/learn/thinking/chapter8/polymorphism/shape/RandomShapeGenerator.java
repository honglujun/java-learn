package com.learn.thinking.chapter8.polymorphism.shape;

import java.util.Random;

/**
 * 工厂模式
 *
 * @author win10
 */
public class RandomShapeGenerator {
    private Random rand = new Random(47);

    public Shape next() {
        switch (rand.nextInt(3)) {
            default:
            case 0:
                return new Circle();
            case 1:
                return new Square();
            case 2:
                return new Triangle();
        }
    }
}
