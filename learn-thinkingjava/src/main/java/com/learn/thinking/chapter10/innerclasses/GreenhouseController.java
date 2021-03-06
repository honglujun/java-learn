package com.learn.thinking.chapter10.innerclasses;

import com.learn.thinking.chapter10.innerclasses.controller.Event;

/**
 * 这个类是初始化系统的，Restart事件反复运行，主要是因为events数组
 *
 * 注意：Bell的初始化与ThermostatNight，LightOn，LightOff，WaterOn，WaterOff，ThermostatDay的初始化是不同的
 */
public class GreenhouseController {
    public static void main(String[] args) {
        GreenhouseControls gc = new GreenhouseControls();
        gc.addEvent(gc.new Bell(900));
        Event[] events = {
                gc.new ThermostatNight(0),
                gc.new LightOn(200),
                gc.new LightOff(400),
                gc.new WaterOn(600),
                gc.new WaterOff(800),
                gc.new ThermostatDay(1400)
        };
        gc.addEvent(gc.new Restart(2000, events));
        if (args.length == 1) {
            gc.addEvent(
                    new GreenhouseControls.Terminate(
                            new Integer(args[0])));
        }
        gc.run();
    }
}
