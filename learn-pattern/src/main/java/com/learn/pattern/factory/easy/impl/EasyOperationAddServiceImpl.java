package com.learn.pattern.factory.easy.impl;

import com.learn.pattern.factory.easy.EasyOperationService;

/**
 * 加法实现类
 *
 * @author win10
 */
public class EasyOperationAddServiceImpl implements EasyOperationService {

    @Override
    public double getResult(double numberA, double numberB) throws Exception {
        return numberA + numberB;
    }
}
