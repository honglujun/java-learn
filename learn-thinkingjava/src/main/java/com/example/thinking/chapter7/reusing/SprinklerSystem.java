package com.example.thinking.chapter7.reusing;

class WaterSource {
    private String s;

    WaterSource() {
        System.out.println("WaterSource()");
        s = "Constructed";
    }

    @Override
    public String toString() {
        return s;
    }
}

/**
 * 组合语法
 *
 * @author win10
 */
public class SprinklerSystem {
    private String value1, value2, value3, value4;
    private WaterSource source = new WaterSource();
    private int i;
    private float f;

    @Override
    public String toString() {
        return
                "value1 = " + value1 + " " +
                        "value2 = " + value2 + " " +
                        "value3 = " + value3 + " " +
                        "value4 = " + value4 + "\n" +
                        "i = " + i + " " + "f = " + f + " " +
                        "source = " + source;
    }

    public static void main(String[] args) {
        SprinklerSystem sprinklers = new SprinklerSystem();
        System.out.println(sprinklers);
    }
}
/*Output:
WaterSource()
value1 = null value2 = null value3 = null value4 = null
i = 0 f = 0.0 source = Constructed
 */