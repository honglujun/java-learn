package com.example.thinking.chapter8.polymorphism.shape;

/**
 * Square:正方形
 *
 * @author win10
 */
public class Square extends Shape {
    @Override
    public void draw() {
        System.out.println("Square.draw()");
    }

    @Override
    public void erase() {
        System.out.println("Square.erase()");
    }
}
