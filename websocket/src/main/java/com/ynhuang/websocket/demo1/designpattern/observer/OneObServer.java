package com.ynhuang.websocket.demo1.designpattern.observer;

/**
 * @Auther: 018399
 * @Date: 2019/2/28 11:10
 * @Description:
 */
public class OneObServer extends ObServer {


    public OneObServer(Subject subject) {
        super.subject = subject;
        super.subject.addObServer(this);
    }

    @Override
    public void update(int message) {
        System.out.println("当前message为:" + message);
    }
}
