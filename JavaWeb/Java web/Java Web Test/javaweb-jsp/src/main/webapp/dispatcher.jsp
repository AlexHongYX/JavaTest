<%--
  Created by IntelliJ IDEA.
  User: xiaoaxiao
  Date: 2019/9/17
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请求转发与重定向</title>
</head>
<body>
    <%
        // 请求转发
        // request.getRequestDispatcher("request_form.jsp").forward(request,response);
        // 请求重定向
        response.sendRedirect("request_form.jsp");
    %>
</body>
</html>
