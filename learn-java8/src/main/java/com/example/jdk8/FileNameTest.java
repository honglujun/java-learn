package com.example.jdk8;

import java.io.File;

public class FileNameTest {

    public static void main(String[] args) {
        File dir = new File("E:\\cc");
        // 该文件目录下文件全部放入数组
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                System.out.println(fileName);
            }
        }
    }
}
