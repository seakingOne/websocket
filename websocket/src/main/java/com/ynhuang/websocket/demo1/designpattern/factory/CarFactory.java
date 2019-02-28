package com.ynhuang.websocket.demo1.designpattern.factory;

/**
 * @Auther: 018399
 * @Date: 2019/2/28 10:56
 * @Description:
 */
public class CarFactory {

    public static CarUse getUse(String useType){

        if(useType.equalsIgnoreCase("bususe")){
            return new BusUse();
        }
        if(useType.equalsIgnoreCase("smallcaruse")){
            return new SmallCarUse();
        }

        return null;
    }

}
