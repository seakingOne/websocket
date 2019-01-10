package com.ynhuang.websocket.impl;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: 018399
 * @Date: 2019/1/10 14:34
 * @Description:
 */
@Component
@ServerEndpoint(value = "/webSocket/chat/{roomName}")
public class WsServer {

    //使用map来收集session,key为roomName,value为同一个房间的用户
    private static final Map<String, Set<Session>> rooms = new ConcurrentHashMap();

    @OnOpen
    public void connect(@PathParam("roomName") String roomName,
                        Session session) throws Exception {
        // 将session按照房间名来存储，将各个房间的用户隔离
        if (!rooms.containsKey(roomName)) {
            System.out.println("房间不存在，创建一个新的房间...");
            // 创建房间不存在时，创建房间
            Set<Session> room = new HashSet<>();
            // 添加用户
            room.add(session);
            rooms.put(roomName, room);
        } else {
            // 房间已存在，直接添加用户到相应的房间
            rooms.get(roomName).add(session);
        }
        System.out.println("session为：" + session + "的用户进入" + roomName + "房间");
    }

    @OnClose
    public void disConnect(@PathParam("roomName") String roomName,
                           Session session) {
        rooms.get(roomName).remove(session);
        System.out.println("session为：" + session + "的用户离开了" + roomName + "房间");
    }

    @OnMessage
    public void receiveMsg(@PathParam("roomName") String roomName,
                           String msg, Session session) throws Exception {
        // 此处应该有html过滤
        msg = "当前用户sessionId为:" + session.getId() + ",发送的消息为:" + msg;
        System.out.println(roomName + "房间的用户收到的消息为:" + msg);
        // 接收到信息后进行广播
        broadcast(roomName, msg);
    }

    // 按照房间名进行广播
    public static void broadcast(String roomName, String msg) throws Exception {
        for (Session session : rooms.get(roomName)) {
            session.getBasicRemote().sendText(msg);
        }
    }

}
