package com.example.thinking.chapter5;

class Person{
    public void eat(Apple apple){
        Apple peeled = new Apple();
        System.out.println("Yummy");
    }
}

class Peeler{
    static Apple peel(Apple apple){
        // remove peel 去皮
        return apple;// Peeled  去皮后
    }
}
class Apple{
    Apple getPeeled(){
        return Peeler.peel(this);
    }
}

public class PassingThis {
    public static void main(String[] args) {
        new Person().eat(new Apple());
    }
}
