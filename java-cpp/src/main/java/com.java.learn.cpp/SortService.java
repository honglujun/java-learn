package com.java.learn.cpp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortService {
    /**
     * 用于隔开名称和id
     */
    private static final String KEY_SPLIT = ":";

    // 测试的主方法
    public static void main(String[] args) {
        List<CarParmCarBaseInfo> cList = new ArrayList<CarParmCarBaseInfo>();
        CarParmCarBaseInfo carInfo1 = new CarParmCarBaseInfo();
        carInfo1.setCarBrand("宝马");
        carInfo1.setCarBrandId("10010");

        CarParmCarBaseInfo carInfo2 = new CarParmCarBaseInfo();
        carInfo2.setCarBrand("宾利");
        carInfo2.setCarBrandId("10012");

        CarParmCarBaseInfo carInfo3 = new CarParmCarBaseInfo();
        carInfo3.setCarBrand("大众");
        carInfo3.setCarBrandId("10013");

        CarParmCarBaseInfo carInfo4 = new CarParmCarBaseInfo();
        carInfo4.setCarBrand("东风");
        carInfo4.setCarBrandId("10014");

        CarParmCarBaseInfo carInfo5 = new CarParmCarBaseInfo();
        carInfo5.setCarBrand("奥迪");
        carInfo5.setCarBrandId("10015");

        List<String> list = new ArrayList<String>();

        list.add(carInfo1.getCarBrand() + KEY_SPLIT + carInfo1.getCarBrandId());
        list.add(carInfo2.getCarBrand() + KEY_SPLIT + carInfo2.getCarBrandId());
        list.add(carInfo3.getCarBrand() + KEY_SPLIT + carInfo3.getCarBrandId());
        list.add(carInfo4.getCarBrand() + KEY_SPLIT + carInfo4.getCarBrandId());
        list.add(carInfo5.getCarBrand() + KEY_SPLIT + carInfo5.getCarBrandId());

        for (CarParmCarBaseInfo str : cList) {
            list.add(str.getCarBrand() + KEY_SPLIT + str.getCarBrandId());
        }
        // 调用排序的方法
        SortService sort = new SortService();
        sort.carSort(list);
    }

    /**
     * 排序的方法
     *
     * @param list
     */
    public void carSort(List<String> list) {
        DataUtil sort = new DataUtil();
        Map<String, List<String>> map = sort.sort(list);

        Map<String, List<CarParmCarBaseInfo>> resultMap = new HashMap<String, List<CarParmCarBaseInfo>>();

        // 遍历排序之后的集合
        for (Entry<String, List<String>> listEntry : map.entrySet()) {
            for (String value : listEntry.getValue()) {

                // map中的key是否包含品牌首字母，若存在则把数据处理之后存储到对象里面，然后添加到List里面,
                // 否则新建key然后再把数据处理之后存储到对象里面，然后添加到List里面
                if (resultMap.containsKey(listEntry.getKey())) {
                    CarParmCarBaseInfo carBaseInfo = new CarParmCarBaseInfo();

                    // 按:把品牌名称和id分开
                    String[] brandArray = value.split(KEY_SPLIT);
                    // 品牌名称
                    carBaseInfo.setCarBrand(brandArray[0]);
                    // 品牌id
                    carBaseInfo.setCarBrandId(brandArray[1]);
                    // 品牌名称首字母
                    carBaseInfo.setCarBrandFirstLetter(listEntry.getKey());

                    resultMap.get(listEntry.getKey()).add(carBaseInfo);

                } else {
                    List<CarParmCarBaseInfo> list2 = new ArrayList<>();
                    CarParmCarBaseInfo carBaseInfo = new CarParmCarBaseInfo();

                    // 按:把品牌名称和id分开
                    String[] brandArray = value.split(KEY_SPLIT);
                    // 品牌名称
                    carBaseInfo.setCarBrand(brandArray[0]);
                    // 品牌id
                    carBaseInfo.setCarBrandId(brandArray[1]);
                    // 品牌名称首字母
                    carBaseInfo.setCarBrandFirstLetter(listEntry.getKey());
                    list2.add(carBaseInfo);

                    resultMap.put(listEntry.getKey(), list2);
                }
            }
        }
        // 打印排序后的结果
        for (Entry<String, List<CarParmCarBaseInfo>> entry : resultMap.entrySet()) {
            System.out.println(entry);
        }
    }

}
