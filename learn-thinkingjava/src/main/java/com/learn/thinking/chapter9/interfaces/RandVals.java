package com.learn.thinking.chapter9.interfaces;

import java.util.Random;

public interface RandVals {
    Random RAND = new Random();
    int RANDOM_INT = RAND.nextInt(10);
    long RANDOM_LONG = RAND.nextLong() * 10;
    float RANdOM_FLOAT = RAND.nextLong() * 10;
    double RANDOM_DOUBLE = RAND.nextDouble() * 10;
}
