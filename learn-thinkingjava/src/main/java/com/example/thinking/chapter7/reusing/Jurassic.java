package com.example.thinking.chapter7.reusing;

class SmallBrain {
}

final class Dinosaur {
    int i = 7;
    int j = 1;
    SmallBrain x = new SmallBrain();

    void f() {

    }
}
// 不允许继承final类
//class Futher extends Dinosaur{}

public class Jurassic {
    public static void main(String[] args) {
        Dinosaur n = new Dinosaur();
        n.f();
        // i可以改变
        n.i = 40;
        // j 可以改变
        n.j++;
        System.out.println("n.i = " + n.i);
        System.out.println("n.j = " + n.j);
    }
}
/*Output:
n.i = 40
n.j = 2
 */