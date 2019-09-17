<%--
  Created by IntelliJ IDEA.
  User: xiaoaxiao
  Date: 2019/9/17
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="segments/header.jsp"/>
    <%
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String skill = request.getParameter("skill");
        System.out.println(skill);
    %>
    用户名：<%=username%><br/>
    密码：<%=password%><br/>
    邮箱：<%=email%><br/>
    我是练习时长两年半的<%=username%>，我喜欢<%=skill%><br/>
    <jsp:include page="segments/footer.jsp"/>
</body>
</html>
