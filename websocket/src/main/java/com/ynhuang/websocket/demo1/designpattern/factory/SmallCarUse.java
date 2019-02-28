package com.ynhuang.websocket.demo1.designpattern.factory;

/**
 * @Auther: 018399
 * @Date: 2019/2/28 10:55
 * @Description:
 */
public class SmallCarUse implements CarUse {
    @Override
    public void use() {
        System.out.println("小车运行方式");
    }
}
