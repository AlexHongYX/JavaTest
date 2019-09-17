<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: xiaoaxiao
  Date: 2019/9/17
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>application内置对象</title>
</head>
<body>
    <%
        application.setAttribute("city","Xian");
        application.setAttribute("company","bite");
        application.setAttribute("postcode",710000);
    %>

    <ol>
        <%
            Enumeration<String> enumeration = application.getAttributeNames();
            while (enumeration.hasMoreElements()){
        %>
        <li>
            <%
                String name = enumeration.nextElement();
                Object value = application.getAttribute(name);
                out.print(name);
                out.print("=");
                out.print(value);
            %>
        </li>
        <%
            }
        %>
    </ol>
</body>
</html>
