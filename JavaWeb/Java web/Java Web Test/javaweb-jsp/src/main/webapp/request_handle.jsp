<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: xiaoaxiao
  Date: 2019/9/17
  Time: 10:59
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
        String username = request.getParameter("username");
        String[] skills = request.getParameterValues("skill");

        // 设置密码信息
        request.setAttribute("password","admin");
    %>
    <p>用户名：<%=username%></p>
    <p>用户技能：</p>
    <ol>
        <%
            for (String skill:skills){
        %>
        <li>
            <%=skill%>
        </li>
        <%
            }
        %>
    </ol>
    <p>密码：<% out.print(request.getAttribute("password"));%></p>
    <hr/>
    <p>request的反射调用</p>
    <%
        Class aClass = request.getClass();
        Class[] interfaces = aClass.getInterfaces();
        for (Class c:interfaces){
    %>
        <p>
            <%
                out.print(c.getName());
            %>
        </p>
    <%
        }
    %>
    <hr/>
    请求体的MIME类型 : <%=request.getContentType()%><br>
    协议类型以及版本号 : <%=request.getProtocol()%><br>
    服务器主机名 : <%=request.getServerName()%><br>
    服务器端口号 : <%=request.getServerPort()%><br>
    请求体大小 : <%=request.getContentLength()%><br>
    请求客户端的IP地址 : <%=request.getRemoteAddr()%><br>
    请求的真实路径 : <%=request.getServletContext().getRealPath("request_handle.jsp")%><br>
    请求的上下文路径 : <%=request.getContextPath()%><br>
</body>
</html>
