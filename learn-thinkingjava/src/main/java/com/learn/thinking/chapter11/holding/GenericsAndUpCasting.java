package com.learn.thinking.chapter11.holding;

import java.util.ArrayList;

class GrannySmith extends Apple {
}

class Gala extends Apple {
}

class Fuji extends Apple {
}

class Braeburn extends Apple {
}

/**
 * 可以将Apple的子类型添加到指定为保存Apple对象的容器中
 */
public class GenericsAndUpCasting {
    public static void main(String[] args) {
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(new GrannySmith());
        apples.add(new Gala());
        apples.add(new Fuji());
        apples.add(new Braeburn());
        for (Apple a : apples) {
            System.out.println(a);
        }
    }
}
/*Output:
com.learn.thinking.chapter11.holding.GrannySmith@1540e19d
com.learn.thinking.chapter11.holding.Gala@677327b6
com.learn.thinking.chapter11.holding.Fuji@14ae5a5
com.learn.thinking.chapter11.holding.Braeburn@7f31245a
 */
