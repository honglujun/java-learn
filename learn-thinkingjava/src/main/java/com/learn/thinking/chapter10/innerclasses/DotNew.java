package com.learn.thinking.chapter10.innerclasses;

/**
 * 使用.new
 */
public class DotNew {
    public class Inner{}

    public static void main(String[] args) {
        // 想直接创建内部类的对象，必须使用外部类的对象来创建内部类对象
        DotNew dn = new DotNew();
        DotNew.Inner dni = dn.new Inner();
    }
}
