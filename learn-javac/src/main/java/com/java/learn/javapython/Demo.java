package com.java.learn.javapython;

public class Demo {

    public static void main(String[] args) {
        Clibrary instance = Clibrary.INSTANTCE;
        // 方法一
        int result = instance.test_return_C();

        // 方法二
        String arr = instance.Decrpyt("方法二参数");
        System.out.println(arr);
        System.out.println(""+result);
//        instance.test_a();
    }

}
