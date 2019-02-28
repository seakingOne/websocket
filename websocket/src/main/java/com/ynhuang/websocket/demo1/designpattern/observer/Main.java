package com.ynhuang.websocket.demo1.designpattern.observer;

/**
 * @Auther: 018399
 * @Date: 2019/2/28 11:12
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        Subject subject = new Subject();

        new OneObServer(subject);

        subject.setMessage(102);

    }
}
