package com.ynhuang.websocket.demo1.designpattern.observer;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
/**
 * @Auther: 018399
 * @Date: 2019/2/28 11:05
 * @Description:
 */
public class Subject {

    private static List<ObServer> observers = new ArrayList<>();

    private int message;

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
        update(message);
    }

    public void addObServer(ObServer observer){
        observers.add(observer);
    }

    public void update(int message){
        for (ObServer observer : observers) {
            observer.update(message);
        }
    }


}
