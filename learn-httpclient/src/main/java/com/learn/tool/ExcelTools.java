package com.learn.tool;

/**
 * @author: 111665
 * @date: 2018-09-21 10:51
 */
public class ExcelTools {

    /**
     * 获取连续序列值中某值在列的索引位置
     * （连续序列值：如月份1~12）
     *
     * @param SequenceNum 序列值
     * @param multiple    序列值占用的列数
     * @param range       起始序列值所在列到左边界的间隔列数
     * @return 返回的是列的索引位置
     */
    public static int getColumeNumForSequenceNum(int SequenceNum, int multiple, int range) {
        /* 推导：
         * colume的索引 = 序列值所在列到左边界的间隔列数 (colume的起始索引为0)
         * n = SequenceNum为序列值，n-1为上个序列值
         * m = multiple为此序列占用的列数，如每个月份下有3个数据，multiple的值就为3
         * (n-1)*m 就为连续序列中，起始序列至上个序列占的总列数
         * range为起始序列值所在列到左边界的间隔列数，列A（第一列）到左边界的间隔列数为0
         * 所以可得：colume的索引 = (n-1) * m + range
         */
        return (SequenceNum - 1) * multiple + range;
    }
}
