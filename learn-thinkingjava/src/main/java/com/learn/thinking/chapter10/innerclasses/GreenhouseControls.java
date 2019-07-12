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
     * action()方法的一个示例，将新new的自己的一个对象插入到事件列表中
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
        private Event[] eventList;

        public Restart(long delayTime, Event[] eventList) {
            super(delayTime);
            this.eventList = eventList;
            for (Event e : eventList) {
                addEvent(e);
            }
        }

        /**
         * 由Event对象组成的数组交给Restart，该数组要加到控制器上，
         * 由于Restart()也是一个Event对象，所以同样要加到Restart.action()中，
         * 是系统有规律的重启自己
         */
        @Override
        public void action() {
            for (Event e : eventList) {
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
