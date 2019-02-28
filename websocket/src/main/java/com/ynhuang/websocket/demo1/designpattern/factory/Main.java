package com.ynhuang.websocket.demo1.designpattern.factory;

/**
 * @Auther: 018399
 * @Date: 2019/2/28 10:59
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        CarUse car = CarFactory.getUse("bususe");
        car.use();
    }
}
