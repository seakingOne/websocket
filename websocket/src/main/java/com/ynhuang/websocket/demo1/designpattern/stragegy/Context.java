package com.ynhuang.websocket.demo1.designpattern.stragegy;

/**
 * @Auther: 018399
 * @Date: 2019/2/28 11:03
 * @Description:
 */
public class Context {

    private Stragegy stragegy;

    public Context(Stragegy stragegy) {
        this.stragegy = stragegy;
    }

    public int getResult(int num1, int num2){
        return this.stragegy.todo(num1, num2);
    }
}
