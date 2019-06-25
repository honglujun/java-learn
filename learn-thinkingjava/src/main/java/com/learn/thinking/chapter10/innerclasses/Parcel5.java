package com.learn.thinking.chapter10.innerclasses;

public class Parcel5 {
    public Destination destination(String s) {
        /**
         * PDestination类是destination()方法的一部分，
         * 在destination()方法之外不能访问PDestination
         */
        class PDestination implements Destination {

            private String label;

            private PDestination(String whereTo) {
                label = whereTo;
            }

            @Override
            public String readLabel() {
                System.out.println("PDestination.readLabel(): "+label);
                return label;
            }
        }
        return new PDestination(s);
    }

    public static void main(String[] args) {
        Parcel5 p = new Parcel5();
        Destination d = p.destination("Tasmania");
        d.readLabel();
    }
}
/*
Output:
PDestination.readLabel(): Tasmania
 */