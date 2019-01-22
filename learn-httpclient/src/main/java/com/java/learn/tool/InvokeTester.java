package com.java.learn.tool;

import java.lang.reflect.Method;

/**
 * @Title: invoke的用法测试
 * @Package
 * @Description
 * @Author 111665
 * @CreateDate 2018/08/07/14:47
 * @Version 1.0
 */
public class InvokeTester {

    public int add(int param1, int param2) {
        return param1 + param2;
    }

    public String echo(String mesg) {
        return "echo" + mesg;
    }

    public static void main(String[] args) throws Exception {
        Class classType = InvokeTester.class;
        Object invokertester = classType.newInstance();

        //getMethod第一个参数是方法名，第二个参数是该方法的参数类型，
        //因为存在同方法名不同参数这种情况，所以只有同时指定方法名和参数类型才能唯一确定一个方法
        Method addMethod = classType.getMethod("add", new Class[]{int.class,
                int.class});
        //Method类的invoke(Object obj,Object args[])方法接收的参数必须为对象，
        //如果参数为基本类型数据，必须转换为相应的包装类型的对象。invoke()方法的返回值总是对象，
        //如果实际被调用的方法的返回类型是基本类型数据，那么invoke()方法会把它转换为相应的包装类型的对象，
        //再将其返回
        Object result = addMethod.invoke(invokertester, new Object[]{
                new Integer(100), new Integer(200)});
        //在jdk5.0中有了装箱 拆箱机制 new Integer(100)可以用100来代替，系统会自动在int 和integer之间转换
        System.out.println(result);

        Method echoMethod = classType.getMethod("echo",
                new Class[]{String.class});
        result = echoMethod.invoke(invokertester, new Object[]{"hello"});
        System.out.println(result);
    }

}
