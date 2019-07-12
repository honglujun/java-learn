package com.learn.thinking.chapter10.innerclasses;

interface Counter {
    int next();
}

/**
 * 局部内部类（getCounter()）与匿名内部类（getCounter2()）的比较
 */
public class LocalInnerClass {
    private int count = 0;

    /**
     * jdk8中可以不加final
     *
     * @param name
     * @return
     */
    Counter getCounter(final String name) {
        /**
         * An local inner class
         */
        class LocalCounter implements Counter {

            public LocalCounter() {
                // Local inner class can have a constructor
                System.out.println("LocalCounter()");
            }

            @Override
            public int next() {
                System.out.print(name);
                return count++;
            }
        }
        return new LocalCounter();
    }

    Counter getCounter2(final String name) {
        return new Counter() {
            // 相当于构造方法
            {
                System.out.println("Counter()");
            }

            @Override
            public int next() {
                System.out.print(name);
                return count++;
            }
        };
    }

    public static void main(String[] args) {
        LocalInnerClass lic = new LocalInnerClass();
        Counter
                c1 = lic.getCounter("Local inner "),
                c2 = lic.getCounter2("Anonymous inner ");
        // 调用局部内部类
        for (int i = 0; i < 3; i++) {
            System.out.println(c1.next());
        }
        // 调用匿名内部类
        for (int i = 0; i < 5; i++) {
            System.out.println(c2.next());
        }
    }
}
/*Output:
LocalCounter()
Counter()
Local inner 0
Local inner 1
Local inner 2
Anonymous inner 3
Anonymous inner 4
Anonymous inner 5
Anonymous inner 6
Anonymous inner 7
 */
