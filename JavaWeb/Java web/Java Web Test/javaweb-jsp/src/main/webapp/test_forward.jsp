<%--
  Created by IntelliJ IDEA.
  User: xiaoaxiao
  Date: 2019/9/17
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
%>
<jsp:forward page="test_user.jsp">
    <jsp:param name="email" value="9217@qq.com"/>
    <jsp:param name="skill" value="唱跳Rap篮球"/>
</jsp:forward>
</body>
</html>
