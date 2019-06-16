package com.example.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Test1 {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3);
//
//        for(int i = 0;i<list.size();i++){
//            System.out.println(list.get(i));
//        }
//
//        System.out.println("=================");
//
//        for(Integer i : list){
//            System.out.println(i);
//        }
//
//        System.out.println("=================");

        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
        System.out.println("=================");

        list.forEach(integer -> {
            System.out.println(integer);
        });
        System.out.println("=================");

        // method reference 方法引用的方式创建函数式接口的实现
        list.forEach(System.out::println);
    }
}
/*
Output:
1
2
3
=================
1
2
3
=================
1
2
3
=================
 */
