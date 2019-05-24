package com.example.thinking.chapter5.initialization;

/**
 * @author win10
 */
public class DynamicArray {
    public static void main(String[] args) {
        Other.main(new String[]{
                "fiddle", "de", "dum"
        });
    }
}

class Other {
    public static void main(String[] args) {
        for (String s : args) {
            System.out.print(s + " ");
        }
    }
}
/*Output:
fiddle de dum
 */