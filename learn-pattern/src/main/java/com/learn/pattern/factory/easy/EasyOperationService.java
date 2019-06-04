package com.learn.pattern.factory.easy;

/**
 * 数据操作接口类
 *
 * @author win10
 */
public interface EasyOperationService {

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
