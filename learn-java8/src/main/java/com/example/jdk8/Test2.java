package com.example.jdk8;

@FunctionalInterface
interface MyInterface {
    void test();

    /**
     * 该方法重写了Object中的toString()方法
     * 该方法不算是抽象方法
     *
     * @return
     */
    String toString();
}

public class Test2 {
    public void myTest(MyInterface myInterface) {
        System.out.println(1);
        myInterface.test();
        System.out.println(2);
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        test2.myTest(new MyInterface() {
            @Override
            public void test() {
                System.out.println("myTest");
            }
        });
        System.out.println("=============");

        test2.myTest(() -> {
            System.out.println("myTest Lambda表达式1");
            System.out.println("myTest Lambda表达式2");
        });

        System.out.println("=============");

        MyInterface myInterface = () -> {
            System.out.println("hello");
        };

        // 打印myInterface对象
        System.out.println(myInterface.getClass());
        // 打印myInterface父类
        System.out.println(myInterface.getClass().getSuperclass());
        // 打印myInterface实现了哪些接口
        System.out.println(myInterface.getClass().getInterfaces()[0]);
    }
}
/*
Output:
1
myTest
2
=============
1
myTest Lambda表达式1
myTest Lambda表达式2
2
=============
class com.example.jdk8.Test2$$Lambda$2/1831932724
class java.lang.Object
interface com.example.jdk8.MyInterface
 */
