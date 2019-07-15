package com.learn.thinking.chapter11.holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AddingGroups {
    public static void main(String[] args) {
        Collection<Integer> c = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        Integer[] moreInts = {6,7,8,9,10};
        c.addAll(Arrays.asList(moreInts));
        Collections.addAll(c,11,12,13,14,15);
        Collections.addAll(c,moreInts);
        List<Integer> list = Arrays.asList(16,17,18,19,20);
        list.set(1, 99);
        // Runtime error because the underlying array cannot be resized
        // 运行时错误，因为基础数组无法调整大小
        list.add(21);
    }
}
