package com.java.learn.javac;

public class JniUtil {

    public static native String getSec();//.so中的方法名

    static{
        System.loadLibrary("jnitest");//加载.so
    }

}
