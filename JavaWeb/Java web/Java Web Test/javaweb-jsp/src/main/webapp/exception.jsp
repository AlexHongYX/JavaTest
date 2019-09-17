<%--
  Created by IntelliJ IDEA.
  User: xiaoaxiao
  Date: 2019/9/17
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error_page.jsp" %>
<html>
<head>
    <title>异常测试页面</title>
</head>
<body>
    <%
        int value = 10/0;
        out.print(value);
    %>
</body>
</html>
