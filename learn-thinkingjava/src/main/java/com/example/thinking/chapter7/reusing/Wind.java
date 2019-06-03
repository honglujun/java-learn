package com.example.thinking.chapter7.reusing;
class Instrument{
    public void play(){
        System.out.println("Instrument.play()");
    }
    static void tune(Instrument i){
        i.play();
    }
}
public class Wind extends Instrument{
    public static void main(String[] args) {
        Wind flute = new Wind();
        // 向上转型
        Instrument.tune(flute);
        // 通过类引用的形式访问play()方法
        flute.play();

    }
}
/*Output:
Instrument.play()
Instrument.play()
 */