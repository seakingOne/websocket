package com.ynhuang.websocket.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 018399
 * @Date: 2019/2/21 16:26
 * @Description:
 */
public class Main {

    public static void main(String[] args) {

        List<Object> lists = new ArrayList<>();

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list2.add("1");
        list2.add("2");

        lists.addAll(list1);
        lists.addAll(list2);

        System.out.println(lists);

    }

}
