package com.wangbin.websocket.chat.model;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wangbin on 2017/11/26.
 */
public class Message {

    private String welcome;

    private List<String> usernames;

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

    // 将对象message数据结构转换为json字符串
    public String toJson() {
        return gson.toJson(this);
    }

    private static Gson gson = new Gson();
}
