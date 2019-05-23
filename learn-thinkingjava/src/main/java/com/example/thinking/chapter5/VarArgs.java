package com.example.thinking.chapter5;

class A {
}

/**
 * 可变参数列表
 *
 * @author win10
 */
public class VarArgs {
    static void printArray(Object[] args) {
        for (Object obj : args) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printArray(new Object[]{
                new Integer(47), new Float(3.14), new Double(11.11)
        });
        printArray(new Object[]{
                "one", "two", "three"
        });
        printArray(new Object[]{
                new A(),new A(),new A()
        });
    }
}
/*Output:
47
3.14
11.11

one
two
three

com.example.thinking.chapter5.A@3cd1a2f1
com.example.thinking.chapter5.A@2f0e140b
com.example.thinking.chapter5.A@7440e464
 */