package com.example.thinking.chapter5;

/**
 * 可变参数列表
 *
 * @author win10
 */
public class NewVarArgs {
    static void printArray(Object... args) {
        for (Object obj : args) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Can take individual elements
        printArray(new Integer(47), new Float(3.14),
                new Double(11.11));
        printArray(47, 3.14F, 11.11);
        printArray("one", "two", "three");
        printArray(new A(), new A(), new A());
        // Or an array
        printArray((Object[]) new Integer[]{1, 2, 3, 4});
        // Empty list is OK
        printArray();
    }
}
/*Output:
47 3.14 11.11
47 3.14 11.11
one two three
com.example.thinking.chapter5.A@3cd1a2f1 com.example.thinking.chapter5.A@2f0e140b com.example.thinking.chapter5.A@7440e464
1 2 3 4
 */
