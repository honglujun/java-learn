package com.learn.pattern.factory.factory.operation;

/**
 * 数据操作接口类(加减乘除)
 *
 * @author win10
 */
public interface FactoryOperationService {

    /**
     * 数据操作接口
     *
     * @param numberA
     * @param numberB
     * @return
     * @throws Exception
     */
    double getResult(double numberA, double numberB) throws Exception;
}
