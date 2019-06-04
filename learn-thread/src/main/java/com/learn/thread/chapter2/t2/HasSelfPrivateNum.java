package com.learn.thread.chapter2.t2;

/**
 * @Title:
 * @Package
 * @Description
 * @Author 111665
 * @CreateDate 2018/09/29/14:36
 * @Version 1.0
 */
public class HasSelfPrivateNum {

    private int num = 0;

    public void addI(String userName) {
        try {
            if (userName.equals("a")) {
                num = 100;
                System.out.println(" a set over!");
                Thread.sleep(2000);
            } else {
                num = 200;
                System.out.println(" b set over!");
            }
            System.out.println(userName + " num" + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
