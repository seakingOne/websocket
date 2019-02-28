package com.ynhuang.websocket.demo1.designpattern.observer;

/**
 * @Auther: 018399
 * @Date: 2019/2/28 11:06
 * @Description:
 */
public abstract class ObServer {

    protected Subject subject;

    public abstract void update(int message);
}
