<%--
  Created by IntelliJ IDEA.
  User: xiaoaxiao
  Date: 2019/9/17
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        session.setAttribute("userName","hehe");
    %>
    用户名为：<%=pageContext.getSession().getAttribute("userName")%>

    <%--页面跳转与包含--%>
    <%
        // 页面跳转(一次请求)
        pageContext.forward("login.jsp");
        // 页面包含
        //pageContext.include("login.jsp");
    %>
</body>
</html>
