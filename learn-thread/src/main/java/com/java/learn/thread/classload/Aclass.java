package com.java.learn.thread.classload;

/**
 * @Title:
 * @Package
 * @Description
 * @Author 111665
 * @CreateDate 2018/10/20/21:22
 * @Version 1.0
 */
public abstract class Aclass {

    public Bclass func(){
        Bclass bc = new Bclass();
        return bc;
    }
}
