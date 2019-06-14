package com.learn.thinking.chapter9.interfaces;

interface Monster {
    /**
     * 对…构成危险
     */
    void menace();
}

interface DangerousMonster extends Monster {
    void destroy();
}

/**
 * 致命
 */
interface Lethal {
    void kill();
}

/**
 * 接口可以多继承，用逗号隔开
 */
interface Vampire extends DangerousMonster, Lethal {
    void drinkBlood();
}

class DragonZilla implements DangerousMonster {

    @Override
    public void menace() {

    }

    @Override
    public void destroy() {

    }
}

class VeryBadVampire implements Vampire {

    @Override
    public void menace() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void kill() {

    }

    @Override
    public void drinkBlood() {

    }
}

/**
 * 恐怖表演
 *
 * @author win10
 */
public class HorrorShow {

    static void u(Monster b) {
        b.menace();
    }

    static void v(DangerousMonster d) {
        d.menace();
        d.destroy();
    }

    static void w(Lethal l) {
        l.kill();
    }

    public static void main(String[] args) {
        DangerousMonster barney = new DragonZilla();
        u(barney);
        v(barney);
        Vampire vlad = new VeryBadVampire();
        u(vlad);
        v(vlad);
        w(vlad);
    }
}
