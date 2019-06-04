package com.learn.pattern.factory.easy.impl;

import com.learn.pattern.factory.easy.EasyOperationService;

/**
 * 减法实现类
 *
 * @author win10
 */
public class EasyOperationSubServiceImpl implements EasyOperationService {
    @Override
    public double getResult(double numberA, double numberB) throws Exception {
        return numberA - numberB;
    }
}
