package com.java.learn.tool;

import java.lang.reflect.Method;

/**
 * @author Acer
 */
public class BaseTools {

    /**
     * 按照给定的属性，将s对象的值移给t对象，如产生错误，该方法会抛出异常
     *
     * @param t          被赋值对象
     * @param s          取值对象
     * @param fieldNames 属性名数组
     * @return 返回被赋值对象t
     * @throws Exception
     */
    public static <T, S> T setChangeField(T t, S s, String[] fieldNames) throws Exception {
        notNull(s, "Source must not be null");
        notNull(t, "Target must not be null");
        Class<? extends Object> class_t = t.getClass();
        Class<? extends Object> class_s = s.getClass();
        for (String fieldName : fieldNames) {
            Class<?> classpt = class_t.getDeclaredField(fieldName).getType();
            fieldName = initialsUppercase(fieldName);
            Method tMethod = class_t.getMethod("set" + fieldName, classpt);
            Method sMethod = class_s.getMethod("get" + fieldName);
            Object svalue = sMethod.invoke(s);
            tMethod.invoke(t, svalue);
        }
        return t;
    }

    /**
     * 判空
     *
     * @param object
     * @param message
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 首字母大写
     */
    public static String initialsUppercase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            // ASCII 中大写字母从65开始，小写字母从97开始，所以这里减去32。
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    public static void main(String[] args) {

    }

}
