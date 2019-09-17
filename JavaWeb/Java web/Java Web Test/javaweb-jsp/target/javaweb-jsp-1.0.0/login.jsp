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
    <title>登录界面</title>
</head>
<body>
    <form method="get" action="doLogin.jsp">
        <div>
            <h5>登录</h5>
        </div>
        <label for="username">用户名：</label>
        <input id="username" type="text" name="username" value=""/><br/>
        <label for="password">密码：</label>
        <input id="password" type="password" name="password"/><br/>
        <input type="submit" value="登录">
    </form>
</body>
</html>
