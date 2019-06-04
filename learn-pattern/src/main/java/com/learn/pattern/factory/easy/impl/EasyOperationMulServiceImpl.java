package com.learn.pattern.factory.easy.impl;

import com.learn.pattern.factory.easy.EasyOperationService;

/**
 * 乘法实现类
 *
 * @author win10
 */
public class EasyOperationMulServiceImpl implements EasyOperationService {
    @Override
    public double getResult(double numberA, double numberB) throws Exception {
        return numberA*numberB;
    }
}
