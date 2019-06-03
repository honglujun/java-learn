package com.example.thinking.chapter7.reusing;

class Shape {
    Shape(int i) {
        System.out.println("Shape constructor");
    }

    void dispose() {
        System.out.println("Shape dispose");
    }
}

class Circle extends Shape {

    /**
     * 圆形
     *
     * @param i
     */
    Circle(int i) {
        super(i);
        System.out.println("Circle constructor");
    }

    @Override
    void dispose() {
        // 消除圆
        System.out.println("Erasing Circle");
        super.dispose();
    }
}

class Triangle extends Shape {

    Triangle(int i) {
        super(i);
        System.out.println("Drawing Triangle");
    }

    @Override
    void dispose() {
        System.out.println("Erasing Triangle");
        super.dispose();
    }
}

class Line extends Shape {

    private int start, end;

    Line(int start, int end) {
        super(start);
        this.start = start;
        this.end = end;
        System.out.println("Drawing Line: " + start + ", " + end);
    }

    @Override
    void dispose() {
        System.out.println("Erasing Line: " + start + ", " + end);
        super.dispose();
    }
}

/**
 * 确保正确的清理
 */
public class CADSystem extends Shape {
    private Circle c;
    private Triangle t;
    private Line[] lines = new Line[3];

    CADSystem(int i) {
        super(i + 1);
        for (int j = 0, len = lines.length; j < len; j++) {
            lines[j] = new Line(j, j * j);
        }
        c = new Circle(1);
        t = new Triangle(1);
        System.out.println("Combined constructor");
    }

    @Override
    public void dispose() {
        System.out.println("CADSystem.dispose()");
        t.dispose();
        c.dispose();
        for (int i = lines.length - 1; i >= 0; i--) {
            lines[i].dispose();

        }
        super.dispose();
    }

    public static void main(String[] args) {
        CADSystem x = new CADSystem(47);
        try {
            // Code and exception handling...
        } finally {
            x.dispose();
        }
    }
}
/*Output:
Shape constructor
Shape constructor
Drawing Line: 0, 0
Shape constructor
Drawing Line: 1, 1
Shape constructor
Drawing Line: 2, 4
Shape constructor
Circle constructor
Shape constructor
Drawing Triangle
Combined constructor
CADSystem.dispose()
Erasing Triangle
Shape dispose
Erasing Circle
Shape dispose
Erasing Line: 2, 4
Shape dispose
Erasing Line: 1, 1
Shape dispose
Erasing Line: 0, 0
Shape dispose
Shape dispose
 */