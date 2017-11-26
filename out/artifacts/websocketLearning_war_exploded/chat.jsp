<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/26
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>chat</title>
</head>
<body>
<div id="content">

</div>
<div id="userList">

</div>

<script>
    window.onload = function () {
        // 获取登录的用户名
        var username = '${sessionScope.username}';
        //进入聊天页面,就打开socket通道
        var ws = null;// 一个ws对象代表一个通信管道
        var target = "ws://localhost:8080/websocket/chatWebSocket?username=" + username;

        if ('WebSocket' in window) {
            ws = new WebSocket(target);
        } else if ('MozWebSocket' in window) {
            ws = new MozWebSocket(target);
        } else {
            alert('WebSocket is not supported by this browser.');
            return;
        }

    }
</script>
</body>
</html>
