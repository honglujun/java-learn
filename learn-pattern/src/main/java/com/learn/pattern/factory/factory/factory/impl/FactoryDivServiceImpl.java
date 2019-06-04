package com.learn.pattern.factory.factory.factory.impl;

import com.learn.pattern.factory.factory.factory.FactoryService;
import com.learn.pattern.factory.factory.operation.FactoryOperationService;
import com.learn.pattern.factory.factory.operation.impl.FactoryOperationDivServiceImpl;

/**
 * 除法工厂实现类
 *
 * @author win10
 */
public class FactoryDivServiceImpl implements FactoryService {
    @Override
    public FactoryOperationService createOperation() {
        return new FactoryOperationDivServiceImpl();
    }
}
