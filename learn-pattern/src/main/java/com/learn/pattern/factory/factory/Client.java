package com.learn.pattern.factory.factory;

import com.learn.pattern.factory.factory.factory.FactoryService;
import com.learn.pattern.factory.factory.operation.FactoryOperationService;

/**
 * @author win10
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // 使用反射机制实例化具体的工厂对象（加减乘除），因为字符串是可以通过变量改变的
        FactoryService addFactory = (FactoryService) Class.forName("com.learn.pattern.factory.factory.factory.impl.FactoryAddServiceImpl").newInstance();
        FactoryService subFactory=(FactoryService) Class.forName("com.learn.pattern.factory.factory.factory.impl.FactorySubServiceImpl").newInstance();
        FactoryService mulFactory=(FactoryService) Class.forName("com.learn.pattern.factory.factory.factory.impl.FactoryMulServiceImpl").newInstance();
        FactoryService DivFactory=(FactoryService) Class.forName("com.learn.pattern.factory.factory.factory.impl.FactoryDivServiceImpl").newInstance();


        // 通过工厂对象创建相应的实例对象
        FactoryOperationService add = addFactory.createOperation();
        FactoryOperationService sub = subFactory.createOperation();
        FactoryOperationService mul = mulFactory.createOperation();
        FactoryOperationService div = mulFactory.createOperation();

        System.out.println(add.getResult(1, 1));
        System.out.println(sub.getResult(1, 1));
        System.out.println(mul.getResult(1, 1));
        System.out.println(div.getResult(1, 1));
    }
}
