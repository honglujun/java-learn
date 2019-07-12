package com.learn.thinking.chapter11.holding;

import java.util.ArrayList;

class Apple {
    private static long counter;
    private final long id = counter++;

    public long id() {
        return id;
    }
}

class Orange {
}

/**
 * ApplesAndOrangesWithoutGenerics--无农药的橘子和苹果
 */
public class ApplesAndOrangesWithoutGenerics {

    @SuppressWarnings("uncheched")
    public static void main(String[] args) {
        ArrayList apples = new ArrayList();
        for (int i = 0; i < 3; i++) {
            apples.add(new Apple());
        }
        apples.add(new Orange());
        for (int i = 0; i < apples.size(); i++) {
            ((Apple)apples.get(i)).id();
        }
    }
}
