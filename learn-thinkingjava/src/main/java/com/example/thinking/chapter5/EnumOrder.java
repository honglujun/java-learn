package com.example.thinking.chapter5;

/**
 * 编译器自动创建ordinal()方法
 * 枚举的使用
 *
 * @author win10
 */
public class EnumOrder {
    public static void main(String[] args) {
        for (Spiciness s : Spiciness.values()) {
            System.out.println(s + ".ordinal " + s.ordinal());
        }
    }
}
/*Output:
NOT.ordinal 0
MILD.ordinal 1
MEDIUM.ordinal 2
HOT.ordinal 3
FLAMING.ordinal 4
 */