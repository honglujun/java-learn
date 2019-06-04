package com.example.thinking.chapter8.polymorphism.shape;

/**
 * Triangle:三角形
 *
 * @author win10
 */
public class Triangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Triangle.draw()");
    }

    @Override
    public void erase() {
        System.out.println("Triangle.erase()");
    }
}
