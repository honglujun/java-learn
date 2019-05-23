package com.example.thinking.chapter5;

/**
 * @author win10
 */
public class OverloadingVarargs2 {
    static void f(float i , Character... args){
        System.out.println("first");
    }
    static void f(Character... args){
        System.out.println("second");
    }

    public static void main(String[] args) {
        f(1,'a');
//        f('a','b'); // 演示的时候放开注释
    }
}
/*
编译报错：
Error:(13, 9) java: 对f的引用不明确
  com.example.thinking.chapter5.OverloadingVarargs2
  中的方法 f(float,java.lang.Character...)
  和 com.example.thinking.chapter5.OverloadingVarargs2
  中的方法 f(java.lang.Character...) 都匹配
 */