package com.learn.thinking.chapter7.reusing;

class Gizmo {
    public void spin() {
    }
}

public class FinalArguments {
    void with(final Gizmo g) {
        //! g = new Gizmo(); // g is final
    }

    void without(Gizmo g) {
        g = new Gizmo();// g is not final
        g.spin();
    }

//    void f(final int i) { i++; }// can't change

    /**
     * you can only read from a final primitive
     *
     * @param i
     * @return
     */
    int g(final int i) {
        return i + 1;
    }

    public static void main(String[] args) {
        FinalArguments bf = new FinalArguments();
        bf.without(null);
        bf.with(null);
    }
}
