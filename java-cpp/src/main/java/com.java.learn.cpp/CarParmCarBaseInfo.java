package com.java.learn.cpp;

public class CarParmCarBaseInfo {
    /** 品牌首字母 */
    private String carBrandFirstLetter;

    /** 品牌名称 */
    private String carBrand;

    /** 品牌ID */
    private String carBrandId;

    public String getCarBrandFirstLetter() {
        return carBrandFirstLetter;
    }

    public void setCarBrandFirstLetter(String carBrandFirstLetter) {
        this.carBrandFirstLetter = carBrandFirstLetter;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(String carBrandId) {
        this.carBrandId = carBrandId;
    }

    @Override
    public String toString() {
        return "CarParmCarBaseInfo{" +
                "carBrandFirstLetter='" + carBrandFirstLetter + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", carBrandId='" + carBrandId + '\'' +
                '}';
    }
}
