package com.ynhuang.websocket.demo1.designpattern.singleton;

/**
 * @Auther: 018399
 * @Date: 2019/2/27 16:01
 * @Description:
 */
public class Singleton2 {

    private static Singleton2 singleton2 = new Singleton2();

    private Singleton2() {

    }

    public static Singleton2 getInstance(){
        return singleton2;
    }
}
