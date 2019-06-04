package com.learn.pattern.factory.factory.operation.impl;

import com.learn.pattern.factory.factory.operation.FactoryOperationService;

/**
 * 减法实现类
 *
 * @author win10
 */
public class FactoryOperationSubServiceImpl implements FactoryOperationService {
    @Override
    public double getResult(double numberA, double numberB) throws Exception {
        return numberA - numberB;
    }
}
