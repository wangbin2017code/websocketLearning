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
<div id="container" style="border: 1px solid black;width: 400px;height: 400px;float: left">
    <div id="content" style="height: 350px;">
    </div>
    <div style="border-top: 1px solid black;width: 400px;height: 50px;">
        <input id="msg"/>
        <button>send</button>
    </div>
</div>
<div id="userList" style="border: 1px solid black;width: 100px;height: 400px;float: left;">

</div>

<script>
    // 获取登录的用户名
    var username = '${sessionScope.username}';
    //进入聊天页面,就打开socket通道
    var ws = null;// 一个ws对象代表一个通信管道
    var target = "ws://localhost:8080/websocket/chatWebSocket?username=" + username;

    window.onload = function () {

        if ('WebSocket' in window) {
            ws = new WebSocket(target);
        } else if ('MozWebSocket' in window) {
            ws = new MozWebSocket(target);
        } else {
            alert('WebSocket is not supported by this browser.');
            return;
        }

        // 接受服务器端的信息
        ws.onmessage=function(event){
            eval("var msg="+event.data+";");
            if(undefined!=msg.welcome){
                $("#content").append(msg.welcome);
                if(undefined!=msg.usernames){
                    $("#userList").html('');
                    $(msg.usernames).each(function(){
                        $("#userList").append(this+"<br/>");
                    });
                }
            }
        }
    }
</script>
<script src="static/js/jquery-3.2.1.js"></script>
</body>
</html>
