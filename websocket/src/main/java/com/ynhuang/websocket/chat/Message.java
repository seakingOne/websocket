package com.ynhuang.websocket.chat;

import lombok.Data;

/**
 * @Auther: 018399
 * @Date: 2019/2/21 09:58
 * @Description:
 */
@Data
public class Message {

    private int sender;

    private int receiver;

    private String msg;

}
