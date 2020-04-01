package com.learn.thinking.chapter11.holding;

import com.learn.thinking.chapter17.net.mindview.util.TextFile;

import java.util.Set;
import java.util.TreeSet;

public class UniqueWords {
    public static void main(String[] args) {
        Set<String> words = new TreeSet<>(
                new TextFile("\\learn-thinkingjava\\src\\main\\java\\com\\learn\\thinking\\chapter11\\holding\\SetOperations.java","\\W+")
        );
        System.out.println(words);
    }
}
