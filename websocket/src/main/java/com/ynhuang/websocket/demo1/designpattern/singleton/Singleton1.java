package com.ynhuang.websocket.demo1.designpattern.singleton;

/**
 * @Auther: 018399
 * @Date: 2019/2/27 15:57
 * @Description:
 */
public class Singleton1 {

    private static Singleton1 singleton1;
    private static Object object;

    private Singleton1(){

    }

    public static Singleton1 getInstance(){

        synchronized (object) {
            if(singleton1 == null){
                singleton1 = new Singleton1();
            }
        }

        return singleton1;
    }

}
