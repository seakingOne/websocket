package com.ynhuang.websocket.demo2;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;

/**
 * @Auther: 018399
 * @Date: 2019/2/21 09:30
 * @Description:
 *
 *  使用websocket需要考虑的问题：
 *  1、浏览器的兼容问题，兼容的环境版本：在Spring框架中使用WebSocket，
 *  需要 Spring4以上的版本；Spring4最低支持 jdk6，官方建议使用 jdk7+
 *
 *  2、web容器版本，tomcat7.0+，jetty9.1+，webLogic12.1.3+
 *
 *  3、对于达到一定时间，连接被断开，一设置nginx的超时时间，二
 *  应用层心跳，假如设置10min/次；对于长时间的长连接问题处理？服务端定时调度扫没有消息传输的客户端连接，一般设置为心跳机制时间的10倍
 *
 *  4、对于客户端异常、以及服务端异常处理
 *
 *  5、确定当前连接设置的最大连接数
 *
 *  6、数据传输的粘包问题？
 *
 *  7、文件传输是否需要加密
 *
 */
@Slf4j
@ServerEndpoint(value = "/chat/{userId}")
@Component
public class WebSocketServer {

    /*
 New Connected
  */
    @OnOpen
    public void onOpen(@PathParam("userId") String userId ,
                       Session session){
        log.info("[WebSocketServer] Connected : userId = "+ userId);
        WebSocketUtils.add(userId , session);
    }

    /*
    Send Message
     */
    @OnMessage
    public String onMessage(@PathParam("userId") String userId,
                            String message) throws IOException {

        Message messageObject = new ObjectMapper().readValue(message, Message.class);

        log.info("[WebSocketServer] Received Message : userId = "+ userId + " , message = " + messageObject.toString());
        if (messageObject.getMsg().equals("heart")){
            return "heart";
        }else{
            log.info("进入非心跳消息....");
            WebSocketUtils.receive(userId , message);
            //给指定人发送消息
            Map<String, Session> clients = WebSocketUtils.getClients();
            if(clients.get(String.valueOf(messageObject.getReceiver())) != null){
                WebSocketUtils.sendMessage(String.valueOf(messageObject.getReceiver()), message);
                return "Got your message ("+ message.toString() +").";
            } else {
                log.error("当前用户没有连接!");
                return "error，当前用户没有连接";
            }

        }
    }

    /*
    Errot
     */
    @OnError
    public void onError(@PathParam("userId") String userId,
                        Throwable throwable,
                        Session session) {
        log.info("[WebSocketServer] Connection Exception : userId = "+ userId + " , throwable = " + throwable.getMessage());
        WebSocketUtils.remove(userId);
    }

    /*
    Close Connection
     */
    @OnClose
    public void onClose(@PathParam("userId") String userId,
                        Session session) {
        log.info("[WebSocketServer] Close Connection : userId = " + userId);
        WebSocketUtils.remove(userId);
    }


}
