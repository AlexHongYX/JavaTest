<%--
  Created by IntelliJ IDEA.
  User: Deleteorus
  Date: 2019/6/9
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试内置对象out</title>
</head>
<body>
    <h1>测试内置对象out</h1>
    <%
        out.println("<h2>咏菊</h2>");
        out.println("待到秋来九月八<br>");
        out.println("我花开时百花杀<br>");
        out.println("冲天香阵透长安<br>");
        out.println("满城尽带黄金甲<br>");
    %>
    <p>缓冲区大小：<%=out.getBufferSize()%></p>
    <p>缓冲器剩余：<%=out.getRemaining()%></p>
    <p>是否自动清空缓存区：<%=out.isAutoFlush()%></p>
</body>
</html>
