package com.ynhuang.websocket.demo1.designpattern.singleton;

/**
 * @Auther: 018399
 * @Date: 2019/2/27 16:03
 * @Description:
 */
public class Singleton3 {

    private Singleton3(){

    }

    public static Singleton3 getInstance(){
        return nowObject.instance.getInstance();
    }

    private enum nowObject {
        instance;

        private Singleton3 singleton3;

        nowObject() {
            this.singleton3 = new Singleton3();
        }

        public Singleton3 getInstance() {
            return singleton3;
        }
    }

}
