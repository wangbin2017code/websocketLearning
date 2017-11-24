<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/24
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>websocket echo示例</title>
  </head>
  <body>
     <button onclick="subOpen()">open</button>&nbsp;<button onclick="subClose()">close</button>
     <input type="text" id="msg"><button onclick="subSend()">send</button>
     <span id="demo"></span>
  </body>
</html>
<script>
  var ws = null;// 一个ws对象代表一个通信管道
  var target = "ws://localhost:8080/websocket/echo";

  //打开websocket连接
  function subOpen(){
    if ('WebSocket' in window) {
      ws = new WebSocket(target);
    } else if ('MozWebSocket' in window) {
      ws = new MozWebSocket(target);
    } else {
      alert('WebSocket is not supported by this browser.');
      return;
    }

    //注册服务器端发送到客户端消息事件
    ws.onmessage = function(event){
      console.info(event);

      // 接受服务器端发送过来的消息，并显示到浏览器
      var obj = document.getElementById("demo");
      // 清空输入框内容
      document.getElementById("msg").value = "";
      obj.innerHTML += event.data;
    }

    // 注册关闭websocket事件
    ws.onclose=function(event){
      console.info(event);
      console.info("关闭websocket");
    }
  }

  // 浏览器发送消息到服务器端
  function subSend(){
    var message = document.getElementById("msg").value;
    // 发送消息到服务器端
    ws.send(message);
  }

  // 关闭webSocket连接
  function subClose(){
    ws.close();
  }
</script>
