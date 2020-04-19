package com.example.jdk8;

import java.util.Optional;

/**
 * 基本的用法
 *
 * @author win10
 */
public class OptionalTest {
    public static void main(String[] args) {
        // 获取空的Optional对象
        Optional<String> optional0 = Optional.empty();
        // 对象不能为空
        Optional<String> optional1 = Optional.of("hello1");
        // 不确定对象是否为空
        Optional<String> optional2 = Optional.ofNullable("hello2");

        // optional2如果存在就打印，不存在就什么都不做，如optional0
        // ifPresent()方法传递的是Consumer接口，
        // 这个接口是函数式接口，这里用的Lambda表达式来传递op -> System.out.println(op)
        // 这个Lambda表达式传递到这个抽象方法void accept(T t)，一个入参op，没有返回值
        // 函数式编程传递的是行为，不是值
        optional2.ifPresent( op -> System.out.println(op));
        optional0.ifPresent( op -> System.out.println(op));

        // optional2如果不存在就打印world2，
        System.out.println(optional2.orElse("world2"));
        // optional0不存在就打印orElse的内容
        System.out.println(optional0.orElse("world0"));
        // optional0不存在就打印orElseGet的内容,入参是Supplier,也是函数式接口，可用Lambda表达式
        System.out.println(optional0.orElseGet(() -> "hello0"));
    }
}
