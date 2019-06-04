package com.example.thinking.chapter8.polymorphism.shape;

/**
 * Circle:圆形
 *
 * @author win10
 */
public class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Circle.draw()");
    }

    @Override
    public void erase() {
        System.out.println("Circle.erase()");
    }
}
