package com.wangbin.websocket.demo;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Echo的webSocket--End Point服务器端.
 * Created by wangbin on 2017/11/24.
 */
@ServerEndpoint("/echo")
public class EchoSocket {

    // 多例--每次都建立一个管道
    public EchoSocket(){
        System.out.println("初始化构造EchoSocket....");
    }

    @OnOpen
    public void open(Session session){
        // 一个session代表一个会话
        System.out.println("sessionId:"+session.getId());
    }

    @OnClose
    public void close(Session session){
        System.out.println("关闭--》sessionId:"+session.getId());
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void message(Session session,String message){
        System.out.println("sessionId:"+session.getId()+" 客户端发送-->"+message);

        // 服务器端向客户端发送消息
        try {
            session.getBasicRemote().sendText("sessionId:"+session.getId()+" 服务端发送-->"+"hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
