package com.learn.pattern.factory.easy.impl;

import com.learn.pattern.factory.easy.EasyOperationService;

/**
 * 除法实现类
 *
 * @author win10
 */
public class EasyOperationDivServiceImpl implements EasyOperationService {
    @Override
    public double getResult(double numberA, double numberB) throws Exception {
        if (numberB == 0) {
            throw new Exception("除数不能为0！");
        }
        return numberA / numberB;
    }
}
