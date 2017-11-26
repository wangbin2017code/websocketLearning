package com.wangbin.websocket.chat.websocket;

import com.wangbin.websocket.chat.model.Message;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 聊天室websocket的EndPoint服务端程序类.
 * Created by wangbin on 2017/11/26.
 */
@ServerEndpoint("/chatWebSocket")
public class ChatSocket {

    // 用户名
    private String username;

    private static List<Session> sessions = new ArrayList<Session>();
    private static List<String> names = new ArrayList<String>();

    @OnOpen
    public void open(Session session) {
        // 当前的weSocket的session对象，不是servlet的session
        String queryString = session.getQueryString();
        System.out.println(queryString);
        username = queryString.split("=")[1];

        this.names.add(username);
        this.sessions.add(session);

        // 构建登入message
        String msg = "欢迎" + this.username + "进入聊天室!!<br/>";
        Message message = new Message();
        // 欢迎学习
        message.setWelcome(msg);
        // 登录用户信息列表
        message.setUsernames(names);

        this.broadcast(sessions, message.toJson());
    }

    @OnClose
    public void close(Session session){
        this.sessions.remove(session);
        this.names.remove(username);

        // 构建退出message
        String msg = "再见"+this.username+"离开聊天室!!<br/>";
        Message message = new Message();
        message.setUsernames(names);
        message.setWelcome(msg);

        this.broadcast(this.sessions,message.toJson());
    }

    // 广播消息方法
    private void broadcast(List<Session> ss, String msg) {

        for (Iterator iterator = ss.iterator(); iterator.hasNext(); ) {
            Session session = (Session) iterator.next();
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
