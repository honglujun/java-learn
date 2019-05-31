package com.example.thinking.chapter6.access;

class Soup1 {

    private Soup1() {
    }

    // (1) Allow creation via static method:
    public static Soup1 makeSoup() {
        return new Soup1();
    }
}

class Soup2 {

    private Soup2() {
    }

    // (2) Create a static object and return a reference
    private static Soup2 ps1 = new Soup2();

    public static Soup2 access() {
        return ps1;
    }

    public void f() {

    }

}

/**
 * @author win10
 */
public class Lunch {
    void testPrivate(){
        // Can't do this! Private constructor:
//        Soup1 soup1 = new Soup1();
    }
    void testStatic(){
        Soup1 soup1 = Soup1.makeSoup();
    }
    // 单例模式
    void testSingleton(){
        Soup2.access().f();
    }
}
