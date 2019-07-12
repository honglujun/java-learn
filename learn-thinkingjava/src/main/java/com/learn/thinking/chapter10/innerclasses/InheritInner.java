package com.learn.thinking.chapter10.innerclasses;

class WithInner {
    class Inner {
    }
}

/**
 * Inherit--继承
 * 内部内的继承：InheritInner只能继承自内部类，而不是外部类
 * 但是要生成一个带有外部类的参数的有参构造函数
 * 构造函数的格式如下代码
 */
public class InheritInner extends WithInner.Inner {

    //! InheritInner() {}

    InheritInner(WithInner wi) {
        wi.super();
    }

    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);
    }
}
