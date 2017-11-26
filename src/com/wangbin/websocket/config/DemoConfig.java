package com.wangbin.websocket.config;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.HashSet;
import java.util.Set;

/**
 * webSocket的配置入口.
 * Created by wangbin on 2017/11/24.
 */
public class DemoConfig implements ServerApplicationConfig{

    // 基于接口式编程websocket
    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> set) {
        return null;
    }

    // 基于注解式编程
    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        System.out.println("webSocket启动，项目中总有:"+scanned.size());
        // 返回 提供过滤的作用
        //获取chat包下的webSocket
        Set<Class<?>> results = new HashSet<Class<?>>();
        for(Class<?> clazz:scanned){
            if(clazz.getPackage().getName().startsWith("com.wangbin.websocket.chat.")){
                // 扫描chat包下的websocket
                results.add(clazz);
            }
        }
        System.out.println("过滤后的websocket:"+results.size());
        return results;
    }
}
