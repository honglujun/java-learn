package com.java.learn.thread.singleton;


import java.util.HashMap;
import java.util.Map;

/**
 * @Title:
 * @Package
 * @Description 控制生成3个单例
 * @Author 111665
 * @CreateDate 2018/09/25/16:39
 * @Version 1.0
 */
public class ThreeSingleton {

    /**
     * 为后面使用的 key 定义一个前缀
     */
    private final static String DEFAULT_PREKEY = "cache";
    /**
     * 定义缓存实例的容器
     */
    private static Map<String, ThreeSingleton> map = new HashMap<>();
    /**
     * 定义初始化实例数目为 1
     */
    private static int number = 1;
    private final static int NUM_MAX = 3;

    private ThreeSingleton() {
    }

    public static synchronized ThreeSingleton getInstance() {
        //通过缓存理念及方式控制数量
        String key = DEFAULT_PREKEY + number;
        ThreeSingleton threeSingleton = map.get(key);
        if (threeSingleton == null) {
            threeSingleton = new ThreeSingleton();
            map.put(key, threeSingleton);
        }
        //实例数目加 1
        number++;
        if (number > NUM_MAX) {
            number = 1;
        }
        return threeSingleton;
    }

    public static void main(String[] args) {
        ThreeSingleton t1 = getInstance();
        ThreeSingleton t2 = getInstance();
        ThreeSingleton t3 = getInstance();
        ThreeSingleton t4 = getInstance();
        ThreeSingleton t5 = getInstance();
        ThreeSingleton t6 = getInstance();
        System.out.println(t1.toString());
        System.out.println(t2.toString());
        System.out.println(t3.toString());
        System.out.println(t4.toString());
        System.out.println(t5.toString());
        System.out.println(t6.toString());
    }
}
