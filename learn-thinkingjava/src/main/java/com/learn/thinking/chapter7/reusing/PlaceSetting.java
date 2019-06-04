package com.learn.thinking.chapter7.reusing;

class Plate {
    /**
     * 主菜
     *
     * @param i
     */
    Plate(int i) {
        System.out.println("Plate constructor");
    }
}

class DinnerPlate extends Plate {
    /**
     * 正餐主菜
     *
     * @param i
     */
    DinnerPlate(int i) {
        super(i);
        System.out.println("DinnerPlate constructor");
    }
}

class Utensil {
    /**
     * 器皿
     *
     * @param i
     */
    Utensil(int i) {
        System.out.println("Utensil constructor");
    }
}

class Spoon extends Utensil {

    /**
     * 勺
     *
     * @param i
     */
    Spoon(int i) {
        super(i);
        System.out.println("Spoon constructor");
    }
}

class Fork extends Utensil {

    /**
     * 叉
     *
     * @param i
     */
    Fork(int i) {
        super(i);
        System.out.println("Fork constructor");
    }
}

class Knife extends Utensil {

    /**
     * 叉
     *
     * @param i
     */
    Knife(int i) {
        super(i);
        System.out.println("Knife constructor");
    }
}

/**
 * A cultural way of doing something
 */
class Custom {
    /**
     * 风俗
     *
     * @param i
     */
    Custom(int i) {
        System.out.println("Custom constructor");
    }
}

public class PlaceSetting extends Custom {
    private Spoon sp;
    private Fork frk;
    private Knife kn;
    private DinnerPlate pl;

    public PlaceSetting(int i) {
        super(i + 1);
        sp = new Spoon(i+2);
        frk = new Fork(i+3);
        kn = new Knife(i+4);
        pl = new DinnerPlate(i+5);
        System.out.println("PlaceSetting constructor");
    }

    public static void main(String[] args) {
        PlaceSetting x = new PlaceSetting(9);
    }
}
/*Output:
Custom constructor
Utensil constructor
Spoon constructor
Utensil constructor
Fork constructor
Utensil constructor
Knife constructor
Plate constructor
DinnerPlate constructor
PlaceSetting constructor
 */