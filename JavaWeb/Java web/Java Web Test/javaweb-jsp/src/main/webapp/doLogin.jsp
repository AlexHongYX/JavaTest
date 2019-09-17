<%--
  Created by IntelliJ IDEA.
  User: xiaoaxiao
  Date: 2019/9/17
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录验证</title>
</head>
<body>
    <%
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.equals("admin")&&password.equals("admin")){
            out.print("<h1>"+"Welcome "+username+"</h1>");
        }else {
            out.print("Incorrect username or password<br/>");
            out.print("<a href='login.jsp'>Login Again</a>");
        }
    %>
</body>
</html>
