<%--
  Created by IntelliJ IDEA.
  User: xiaoaxiao
  Date: 2019/9/17
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>session中的用户名：<%=session.getAttribute("username")%></p>
    <p>session中的密码：<%=session.getAttribute("password")%></p>
    <p>session中的年龄：<%=session.getAttribute("age")%></p>
</body>
</html>
