package com.java.learn.httpclient;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 工具类
 *
 * @param <E>
 * @author Acer
 */
public class BusList<E> extends ArrayList<E> {

    private static final long serialVersionUID = 1L;

    /**
     * 首字母大写
     */
    private String initialsUppercase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            // ASCII 中大写字母从65开始，小写字母从97开始，所以这里减去32
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 获取list中对象的某个属性，返回此属性的list
     *
     * @param FieldName 属性名称
     * @param classF    属性class类
     */
    public <F> List<F> getElementFieldAllOfList(String FieldName, Class<F> classF) throws Exception {
        List<F> resultList = new BusList<F>();
        for (E element : this) {
            Class<? extends Object> classE = element.getClass();
            @SuppressWarnings("unchecked")
            Class<F> type = (Class<F>) classE.getDeclaredField(FieldName).getType();
            Method method = classE.getMethod("get" + initialsUppercase(FieldName));
            F value = type.cast(method.invoke(element));
            resultList.add(value);
        }
        return resultList;
    }

    /**
     * 内部匹配类
     */
    public abstract class Macth {
        /**
         * 匹配元素
         */
        public abstract boolean macthElement(E element);
    }


    /**
     * 根据匹配条件生成新的list
     *
     * @param business 实现匹配
     */
    public List<E> getElementForMatch(Macth macth) {
        List<E> resultList = new BusList<E>();
        for (E element : this) {
            if (macth.macthElement(element)) {
                resultList.add(element);
            }
        }
        return resultList;
    }
}
