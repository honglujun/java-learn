package com.learn.thinking.chapter5.initialization;

import java.util.Arrays;

/**
 * @author win10
 */
public class ArrayInit {
    public static void main(String[] args) {
        Integer[] a = {
                new Integer(1), new Integer(2), 3
        };
        Integer[] b = {
                new Integer(1), new Integer(2), 3
        };
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }
}
/*Output:
[1, 2, 3]
[1, 2, 3]
 */