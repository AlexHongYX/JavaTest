<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: xiaoaxiao
  Date: 2019/9/17
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>内置对象out和response输出内容</title>
</head>
<body>
    <%-- response对象在JSP中输出内容优先于out对象 --%>
    <%
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // JspWriter(即便写在前面，输出优先级也没有response高)
        out.println("out 输出内容<br/>");
        out.flush();

        PrintWriter pw = response.getWriter();
        pw.println("response 输出内容<br/>");
    %>
</body>
</html>
