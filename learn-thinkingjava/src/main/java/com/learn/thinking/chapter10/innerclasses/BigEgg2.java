package com.learn.thinking.chapter10.innerclasses;

class Egg2 {
    protected class Yolk {
        public Yolk() {
            System.out.println("Egg2.Yolk()");
        }

        public void f() {
            System.out.println("Egg2.Yolk.f()");
        }
    }

    /**
     * 比构造方法先加载
     */
    private Yolk y = new Yolk();

    public Egg2() {
        System.out.println("new Egg2()");
    }

    public void insertYolk(Yolk yy) {
        y = yy;
    }

    public void g() {
        y.f();
    }
}

public class BigEgg2 extends Egg2 {

    public class Yolk extends Egg2.Yolk {
        public Yolk() {
            System.out.println("BigEgg2.Yolk()");
        }

        @Override
        public void f() {
            System.out.println("BigEgg2.Yolk.f()");
        }
    }

    public BigEgg2() {
        insertYolk(new Yolk());
    }

    public static void main(String[] args) {
        Egg2 e2 = new BigEgg2();
        e2.g();
    }
}
/*
Output:
Egg2.Yolk()
new Egg2()
Egg2.Yolk()
BigEgg2.Yolk()
BigEgg2.Yolk.f() --这个是e2.g()打印的结果
 */