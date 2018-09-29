package com.java.learn.thread.chapter2.t1;

/**
 * @Title:
 * @Package
 * @Description
 * @Author 111665
 * @CreateDate 2018/09/29/11:51
 * @Version 1.0
 */
public class HasSelfPrivateNum {
    public void addI(String userName) {
        try {
            int num = 0;
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
