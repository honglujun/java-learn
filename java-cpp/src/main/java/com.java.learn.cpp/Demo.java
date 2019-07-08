package com.java.learn.cpp;

import java.io.IOException;

public class Demo {

    public static void main(String[] args) throws IOException {

        Clibrary instance = Clibrary.INSTANCE;

        // test_as()是so 动态库向外暴露的接口
        instance.test_a();

    }

}
