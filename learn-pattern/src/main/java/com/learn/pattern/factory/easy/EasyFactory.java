package com.learn.pattern.factory.easy;

import com.learn.pattern.factory.easy.impl.EasyOperationAddServiceImpl;
import com.learn.pattern.factory.easy.impl.EasyOperationDivServiceImpl;
import com.learn.pattern.factory.easy.impl.EasyOperationMulServiceImpl;
import com.learn.pattern.factory.easy.impl.EasyOperationSubServiceImpl;

/**
 * 简单工厂
 *
 * @author win10
 */
public class EasyFactory {

    /**
     * 简单工厂，根据字符串创建相应的对象
     *
     * @param name（+,-,*,/）
     * @return OperationService接口
     */
    public static EasyOperationService createOperation(String name) {
        EasyOperationService operationObj = null;
        switch (name) {
            case "+":
                operationObj = new EasyOperationAddServiceImpl();
                break;
            case "-":
                operationObj = new EasyOperationSubServiceImpl();
                break;
            case "*":
                operationObj = new EasyOperationMulServiceImpl();
                break;
            case "/":
                operationObj = new EasyOperationDivServiceImpl();
                break;
        }
        return operationObj;
    }
}
