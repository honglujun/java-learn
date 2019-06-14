package com.learn.thinking.chapter9.interfaces.classprocessor;

import java.util.Arrays;

class Processor {
    public String name() {
        return getClass().getSimpleName();
    }

    Object process(Object input) {
        return input;
    }
}

class UpCase extends Processor {
    @Override
    String process(Object input) {
        return ((String) input).toUpperCase();
    }
}

class DownCase extends Processor {
    @Override
    String process(Object input) {
        return ((String) input).toLowerCase();
    }
}

class Splitter extends Processor {
    @Override
    String process(Object input) {
        return Arrays.toString(((String) input).split(" "));
    }
}

/**
 * 策略模式：
 * 创建一个能够根据所传的参数对象的不同而具有不同的行为方法。
 * 这个类方法包含所要执行的算法中固定不变的部分，
 * “策略”包含变化的部分，就是传递进去的参数对象，它包含要执行的代码
 * Processor对象就是一个策略
 * main()方法中有三种不同的策略应用到了String类型的s对象上
 *
 */
public class Apply {
    public static void process(Processor p, Object s) {
        System.out.println("Using Processor " + p.name());
        System.out.println(p.process(s));
    }

    public static final String s = "Disagreement with beliefs is by definition incorrect";

    public static void main(String[] args) {
        process(new UpCase(), s);
        process(new DownCase(), s);
        process(new Splitter(), s);
    }
}
/*
Output:
Using Processor UpCase
DISAGREEMENT WITH BELIEFS IS BY DEFINITION INCORRECT
Using Processor DownCase
disagreement with beliefs is by definition incorrect
Using Processor Splitter
[Disagreement, with, beliefs, is, by, definition, incorrect]
 */