package com.ynhuang.websocket.demo2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ynhuang.websocket.demo2.util.Base64Util;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @Auther: 018399
 * @Date: 2019/2/21 09:30
 * @Description:
 *
 *  使用webSocket需要考虑的问题：
 *  1、浏览器的兼容问题，兼容的环境版本：在Spring框架中使用WebSocket，
 *  需要 Spring4以上的版本；Spring4最低支持 jdk6，官方建议使用 jdk7+
 *
 *  2、web容器版本，tomcat7.0+，jetty9.1+，webLogic12.1.3+
 *
 *  3、对于达到一定时间，连接被断开，一设置nginx的超时时间，二
 *  应用层心跳，假如设置10min/次；对于长时间的长连接问题处理？
 *  服务端定时调度扫没有消息传输的客户端连接，一般设置为心跳机制时间的10倍
 *
 *  4、对于客户端异常、以及服务端异常处理
 *
 *  5、确定当前连接设置的最大连接数
 *
 *  6、数据传输的粘包问题？
 *
 */
@Slf4j
@ServerEndpoint(value = "/chat/{userId}")
@Component
public class WebSocketServer {

    /** 当前文件名 **/
    private String fileName;

    /*
 New Connected
  */
    @OnOpen
    public void onOpen(@PathParam("userId") String userId ,
                       Session session){

        log.info("[WebSocketServer] Connected : userId = "+ userId);

        //1 当前session最大文件传输大小
        session.setMaxBinaryMessageBufferSize(5 * 1024 * 1024);

        //2 添加进入聊天室的用户
        WebSocketUtils.add(userId , session);
    }

    /*
    Send String
     */
    @OnMessage
    public String onMessage(@PathParam("userId") String userId,
                            String message) throws IOException {

        //1 消息实体类转换
        Message messageObject = new ObjectMapper().readValue(message, Message.class);

        log.info("[WebSocketServer] Received Message : userId = "+ userId + " , message = " + messageObject.toString());

        //2 消息处理
        //2.1 心跳
        if (messageObject.getMsg().equals("heart")){
            return "heart";
        }else{

            //2.2 打印收取的信息 展示当前在线人数
            WebSocketUtils.receive(userId , message);

            //2.3 给指定用户发送消息
            if(messageObject.getReceiver() != 0) {
                boolean isSend = WebSocketUtils.sendMessage(String.valueOf(messageObject.getReceiver()), message);
                if(!isSend){
                    log.error("当前用户不在线！");
                    return "当前用户不在线";
                }
                return "Got your message (" + message.toString() + ").";
            }

            //2.4 获取上传的文件名称
            this.fileName = messageObject.getMsg().split(": fileStart")[0];

            return "获取到文件名:" + messageObject.getMsg();

        }
    }

    @OnMessage
    public String onMessage(@PathParam("userId") String userId,
                            byte[] message, Session session) throws IOException {

        //1 存储文件到服务器路径
        File file = new File("/Users/mac/Downloads/" + fileName);//("E://ynhuang//image//" + fileName);

        //2 输入流
        FileOutputStream fe = new FileOutputStream(file,true);

        //3 写文件
        fe.write(message);
        fe.flush();
        fe.close();

        System.out.println(new String(message, "UTF-8"));

        //4 返回信息
        String encode = Base64Util.encode(message);
        String resultStr = IdCard.getResult(encode);

        // TODO: 2/3/19 对应这里的接收者可以写死，比如双方约定 userid_pc userid_app 发送的时候直接对应上即可

        return resultStr;

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
