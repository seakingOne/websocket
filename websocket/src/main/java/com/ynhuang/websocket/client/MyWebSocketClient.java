package com.ynhuang.websocket.client;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;

/**
 * @Auther: 018399
 * @Date: 2019/3/7 18:25
 * @Description:
 */
@Slf4j
@ClientEndpoint
public class MyWebSocketClient {

    private String deviceId;

    private Session session;

    public MyWebSocketClient () {

    }

    public MyWebSocketClient (String deviceId) {
        this.deviceId = deviceId;
    }

    protected boolean start() {
        WebSocketContainer Container = ContainerProvider.getWebSocketContainer();
        String uri = "ws://10.32.41.222:8080/chat/chat/" + deviceId;
        System.out.println("Connecting to " + uri);
        try {
            session = Container.connectToServer(MyWebSocketClient.class, URI.create(uri));
            System.out.println("count: " + deviceId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        System.out.println(1);
        return true;
    }

    public static void main(String[] args) {
        for (int i = 1; i< 2000; i++) {
            MyWebSocketClient wSocketTest = new MyWebSocketClient(String.valueOf(i));
            if (!wSocketTest.start()) {
                System.out.println("测试结束！");
                break;
            }
        }
        System.out.println(1);
    }

}
