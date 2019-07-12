package com.learn.thinking.chapter10.innerclasses;

import com.learn.thinking.chapter10.innerclasses.controller.Controller;
import com.learn.thinking.chapter10.innerclasses.controller.Event;

public class GreenhouseControls extends Controller {
    private boolean light = false;

    /**
     * 开灯的内部类
     */
    public class LightOn extends Event {
        public LightOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            /**
             * Put hardware control code here to physically turn on the light
             * 在此处输入硬件控制代码，以实际开灯
             */
            light = true;
        }

        @Override
        public String toString() {
            return "Light is on";
        }
    }

    /**
     * 关灯的内部类
     */
    public class LightOff extends Event {
        public LightOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            /**
             * Put hardware control code here to physically turn off the light
             * 在此处输入硬件控制代码，以实际关灯
             */
            light = false;
        }

        @Override
        public String toString() {
            return "Light is off";
        }
    }

    private boolean water = false;

    public class WaterOn extends Event {
        public WaterOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            /**
             * Put hardware control code here
             * 在此处输入硬件控制代码
             */
            water = true;
        }

        @Override
        public String toString() {
            return "Greenhouse water is on";
        }
    }

    public class WaterOff extends Event {
        public WaterOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            /**
             * Put hardware control code here
             * 在此处输入硬件控制代码
             */
            water = false;
        }

        @Override
        public String toString() {
            return "Greenhouse water is off";
        }
    }

    // 恒温器
    private String thermostat = "Day";

    /**
     * 夜间恒温器
     */
    public class ThermostatNight extends Event {
        public ThermostatNight(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            /**
             * Put hardware control code here
             * 在此处输入硬件控制代码
             */
            thermostat = "Night";
        }

        @Override
        public String toString() {
            return "Thermostat on night setting";
        }
    }

    public class ThermostatDay extends Event {
        public ThermostatDay(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            /**
             * Put hardware control code here
             * 在此处输入硬件控制代码
             */
            thermostat = "Day";
        }

        @Override
        public String toString() {
            return "Thermostat on day setting";
        }
    }

    /**
     * 控制响铃
     * An example of an action() that insert a new one of itself into the event list
     *
     * action()方法的一个示例，将新new的自己的一个对象插入到事件列表eventList中,
     * 使得在Controller中在将响铃事件结束后还有8个事件（为什么是8个，请debug看的清楚一些）,
     * (最开始就有8个事件，但是在Bell事件的action()是new一个新的Bell事件放在eventList中，这样就有9个事件了，然后这个事件结束会remove()掉一个Bell，所以有8个)
     * 也就是说本来8个eventList中的事件，应该会变成7个，但是在本事件中的action()是添加一个本事件Bell，所以本事件结束后还有8个，
     * 这样造成的结果就是，因为初始化(GreenhouseController)的gc.addEvent(gc.new Bell(900))原因，初始化只加了一次响铃Bell，所以后续需要再加，
     *
     * 而其他的事件在初始化（Controller）的时候是通过Event[] events向Restart的private Event[] events中添加。
     * 所以Restart的private Event[] events会一直有其他的事件（即只要初始化一次，在这个数组中就一直存在）
     *
     * 当然Bell也可以这样做，那就要修改一下Bell的内部类，把action()方法改成跟其他的事件的内部类的action()方法那样，就可以在初始化（Controller）的时候加到数组里面
     */
    public class Bell extends Event {

        public Bell(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            addEvent(new Bell(delayTime));
        }

        @Override
        public String toString() {
            return "Bing!";
        }
    }

    /**
     * 重启系统
     */
    public class Restart extends Event {
        private Event[] events;

        public Restart(long delayTime, Event[] events) {
            super(delayTime);
            this.events = events;
            for (Event e : events) {
                addEvent(e);
            }
        }

        /**
         * 由Event对象组成的数组交给Restart，该数组要加到控制器上，
         * 由于Restart()也是一个Event对象，所以同样要加到Restart.action()中，
         * 使系统有规律的重启自己
         */
        @Override
        public void action() {
            for (Event e : events) {
                // rerun each event--重新运行每个event
                e.start();
                addEvent(e);
            }
            // rerun this Event-- 重新运行此事件
            start();
            addEvent(this);
        }

        @Override
        public String toString() {
            return "Restarting system";
        }
    }

    /**
     * 退出系统
     */
    public static class Terminate extends Event {

        public Terminate(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            System.exit(0);
        }

        @Override
        public String toString() {
            return "Terminating system";
        }
    }

}
