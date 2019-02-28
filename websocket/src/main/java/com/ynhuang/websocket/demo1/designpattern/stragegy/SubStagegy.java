package com.ynhuang.websocket.demo1.designpattern.stragegy;

/**
 * @Auther: 018399
 * @Date: 2019/2/28 11:03
 * @Description:
 */
public class SubStagegy implements Stragegy {
    @Override
    public int todo(int num1, int num2) {
        return num1- num2;
    }
}
