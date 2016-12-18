<%@ page contentType="text/html; charset=UTF-8"%>

<html>
<script>
    var socket;
    if(!window.WebSocket) {
        window.WebSocket = window.MozWebSocket;
    }
    if (window.WebSocket) {
        socket = new WebSocket("ws://localhost:8081/websocket");
        socket.onmessage = function (event) {
            var ta = document.getElementById("responseText");
            ta.value = '';
            ta.value = event.data;
        }
        socket.onopen = function (event) {
            var ta = document.getElementById("responseText");
            ta.value = "打开WebSocket服务正常,浏览器支持WebSocket";
        }
        socket.onclose = function (event) {
            var ta = document.getElementById("responseText");
            ta.value = '';
            ta.value = "WebSocket 关闭!";
        }
    } else{
        alert("抱歉，您的浏览器不支持WebSocket协议!");
    }

    function send(message) {
        if (!window.WebSocket) {return;}
        if (socket.readyState == WebSocket.OPEN) {
            socket.send(message);
        } else {
            alert("WebSocket 连接没有建立成功!");
        }
    }
</script>
<body>
<form onsubmit="return false;">
    <input type="text" name="message" value="Netty 最佳实践" />
    <br><br>
    <input type="button" value="发送 WebSocket 请求消息" onclick="send(this.form.message.value)"/>
    <hr color="blue" />
    <h3>服务端返回应答消息</h3>
    <textarea id="responseText" style="width: 500px;height: 300px;"></textarea>
</form>
</body>
</html>
