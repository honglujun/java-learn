package com.learn.thinking.chapter10.innerclasses;


public class Parcel11 {
    private static class ParcelContents implements Contents {
        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }

    protected static class ParcelDestination implements Destination {

        private String label;

        private ParcelDestination(String whereTo) {
            label = whereTo;
        }

        @Override
        public String readLabel() {
            return label;
        }
        // Nested classes can contain other static elements--嵌套类可以包含其他静态元素
        public static void f() {
        }

        static int x = 10;

        static class AnotherLevel {
            public static void f() {
            }

            static int x = 10;
        }
    }


    public static Destination destination(String s) {
        return new ParcelDestination(s);
    }

    public static Contents contents() {
        return new ParcelContents();
    }

    /**
     * 内部类是static的，即嵌套类。在创建嵌套类的时候，并不需要其外围对象。
     * 不能从嵌套类的对象中访问非静态的外围类对象,只能访问静态的外围类的对象。
     * @param args
     */
    public static void main(String[] args) {

        Contents c = contents();
        Destination d = destination("Tasmania");
    }

}
