package com.learn.thinking.chapter9.interfaces.filters;

/**
 * @author win10
 */
public class Waveform {
    private static long counter;
    private final long id = counter++;

    @Override
    public String toString() {
        return "Waveform " + id;
    }
}
