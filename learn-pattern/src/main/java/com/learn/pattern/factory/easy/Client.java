package com.learn.pattern.factory.easy;

/**
 * 客户端代码
 *
 * @author win10
 */
public class Client {

    public static void main(String[] args) {
        EasyOperationService add = EasyFactory.createOperation("+");
        EasyOperationService sub = EasyFactory.createOperation("-");
        EasyOperationService mul = EasyFactory.createOperation("*");
        EasyOperationService div = EasyFactory.createOperation("/");

        try {
            System.out.println(add.getResult(1, 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println(sub.getResult(1, 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println(mul.getResult(1, 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println(div.getResult(1, 1));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
