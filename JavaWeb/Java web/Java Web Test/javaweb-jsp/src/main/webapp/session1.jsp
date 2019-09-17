<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: xiaoaxiao
  Date: 2019/9/17
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>session内置对象</title>
</head>
<body>
    <%
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date(session.getCreationTime());
        session.setAttribute("username","zhangsan");
        session.setAttribute("password","123");
        session.setAttribute("age",22);
    %>
    <p>session创建时间：<%=sdf.format(date)%></p>
    <p>session的id：<%=session.getId()%></p>
    <p>session过期时间：<%=session.getMaxInactiveInterval()%></p>
    <p>重置session创建时间：
        <%
            // 单位为s
            session.setMaxInactiveInterval(5);
        %>
    </p>
    <a href="session2.jsp">session2</a>
</body>
</html>
