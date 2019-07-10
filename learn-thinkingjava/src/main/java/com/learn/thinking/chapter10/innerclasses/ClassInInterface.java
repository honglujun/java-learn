package com.learn.thinking.chapter10.innerclasses;

/**
 * 嵌套类可以作为接口的一部分，
 * 由于放到接口中的类自动的是public和static的，
 * 所以Test类没有加static，但是Test类就是嵌套类
 */
public interface ClassInInterface {

    void howdy();

    class Test implements ClassInInterface {
        /**
         * 在内部类中实现外围接口
         */
        @Override
        public void howdy() {
            System.out.println("Howdy!");
        }

        public static void main(String[] args) {
            new Test().howdy();
        }
    }
}
/*
Output:
Howdy!
 */
