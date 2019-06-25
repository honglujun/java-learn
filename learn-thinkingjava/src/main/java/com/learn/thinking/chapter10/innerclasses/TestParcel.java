package com.learn.thinking.chapter10.innerclasses;

class Parcel4 {
    private class PContents implements Contents {
        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }

    protected class PDestination implements Destination {

        private String label;

        // 私有化构造器
        private PDestination(String whereTo) {
            System.out.println("inner classes private constructor: " + whereTo);
            label = whereTo;
        }

        @Override
        public String readLabel() {
            return label;
        }
    }

    /**
     * 内部类PContents是private，所以除了Parcel4，没人能访问
     * 向上转型
     */
    public Destination destination(String s) {
        return new PDestination(s);
    }

    public Contents contents() {
        return new PContents();
    }
}

/**
 * 内部类PContents是private，所以除了Parcel4，没人能访问
 */
public class TestParcel {
    public static void main(String[] args) {
        Parcel4 p = new Parcel4();
        Contents c = p.contents();
        Destination d = p.destination("Tasmania");
        d.readLabel();
        c.value();
        // Illegal --can't access private class
        //! Parcel4.PContents pc = p.new PContents();
    }
}
/*
Output:
inner classes private constructor: Tasmania
 */