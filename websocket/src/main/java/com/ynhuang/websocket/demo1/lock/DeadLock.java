package com.ynhuang.websocket.demo1.lock;

/**
 * @Auther: 018399
 * @Date: 2019/2/27 18:46
 * @Description:
 */
public class DeadLock implements Runnable{

    private int flag = 1;

    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {

        if(flag == 1){
            synchronized (o1) {
                synchronized (o2){
                    System.out.println("flag == 1");
                }
            }
        }

        if(flag == 0){
            synchronized (o2) {
                synchronized (o1) {
                    System.out.println("flag == 0");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock1 = new DeadLock();
        deadLock1.flag = 1;

        DeadLock deadLock2 = new DeadLock();
        deadLock2.flag = 0;

        new Thread(deadLock1).start();
        new Thread(deadLock2).start();
    }
}
