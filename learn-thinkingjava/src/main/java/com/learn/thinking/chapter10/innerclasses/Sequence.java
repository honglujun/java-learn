package com.learn.thinking.chapter10.innerclasses;

/**
 * 迭代器模式
 */
interface Selector {
    boolean end();

    Object current();

    void next();
}

public class Sequence {
    private Object[] items;
    private int next = 0;

    public Sequence(int size) {
        items = new Object[size];
    }

    public void add(Object x) {
        if (next < items.length) {
            items[next++] = x;
        }
    }

    private class SequenceSelector implements Selector {

        private int i = 0;

        @Override
        public boolean end() {
            return i == items.length;
        }

        @Override
        public Object current() {
            return items[i];
        }

        @Override
        public void next() {
            if (i < items.length) {
                i++;
            }
        }
    }

    /**
     * 别的方法能以Selector接口为参数
     *
     * @return
     */
    public Selector selector() {
        return new SequenceSelector();
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);
        for (int i = 0; i < 10; i++) {
            sequence.add(Integer.toString(i));
        }
        Selector selector = sequence.selector();
        // 是否到数组的末尾了
        while (!selector.end()) {
            // 打印当前的数据
            System.out.print(selector.current() + " ");
            // 到下一条数据
            selector.next();
        }
    }
}
/*
Output:
0 1 2 3 4 5 6 7 8 9
 */