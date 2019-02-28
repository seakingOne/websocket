package com.ynhuang.websocket.demo1.designpattern.factory;

/**
 * @Auther: 018399
 * @Date: 2019/2/28 10:54
 * @Description:
 */
public class BusUse implements CarUse {
    @Override
    public void use() {
        System.out.println("汽车的运行方式");
    }
}
