package com.learn.pattern.factory.factory.factory;


import com.learn.pattern.factory.factory.operation.FactoryOperationService;

/**
 * 工厂接口
 *
 * @author win10
 */
public interface FactoryService {
    /**
     * 创建数据操作接口
     *
     * @return
     */
    FactoryOperationService createOperation();
}
