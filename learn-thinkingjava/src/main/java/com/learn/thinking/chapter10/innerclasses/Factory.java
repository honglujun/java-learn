package com.learn.thinking.chapter10.innerclasses;

interface Service {
    void method1();

    void method2();
}

interface ServiceFactory {
    Service getService();
}

class Implementation1 implements Service {
    private Implementation1() {
    }

    @Override
    public void method1() {
        System.out.println("Implementation1 method1");
    }

    @Override
    public void method2() {
        System.out.println("Implementation1 method2");
    }

    public static ServiceFactory factory = () -> new Implementation1();
}

class Implementation2 implements Service {
    private Implementation2() {
    }

    @Override
    public void method1() {
        System.out.println("Implementation2 method1");
    }

    @Override
    public void method2() {
        System.out.println("Implementation2 method2");
    }

    /**
     * 可以用lambda表达式来写，同上
     */
    public static ServiceFactory factory = new ServiceFactory() {
        @Override
        public Service getService() {
            return new Implementation2();
        }
    };
}

/**
 * Implementation1和Implementation2的构造器都可以是private的，并且没有任何必要
 * 去创建作为工厂的具名类。
 *
 */
public class Factory {

    public static void serviceConsumer(ServiceFactory fac) {
        Service s = fac.getService();
        s.method1();
        s.method2();
    }

    public static void main(String[] args) {
        serviceConsumer(Implementation1.factory);
        // Implementations are completely interchangeable;--实现是完全可以互换的;
        serviceConsumer(Implementation2.factory);
    }
}
/*
Output:
Implementation1 method1
Implementation1 method2
Implementation2 method1
Implementation2 method2
 */