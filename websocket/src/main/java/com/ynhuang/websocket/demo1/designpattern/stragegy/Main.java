package com.ynhuang.websocket.demo1.designpattern.stragegy;

/**
 * @Auther: 018399
 * @Date: 2019/2/28 11:04
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        Context context = new Context(new AddStagegy());
        System.out.println(context.getResult(1,3));
    }

}
