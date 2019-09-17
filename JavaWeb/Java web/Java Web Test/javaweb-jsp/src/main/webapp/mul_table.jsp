<%@ page import="java.io.IOException" %><%--
  Created by IntelliJ IDEA.
  User: xiaoaxiao
  Date: 2019/9/17
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>九九乘法表</title>
</head>
<body>
    <div>
        <%!
            String printTable(){
                StringBuilder sb = new StringBuilder();
                sb.append("<h1>").append("基于表达式").append("</h1>");
                for (int i=1;i<10;i++){
                    for (int j=1;j<i;j++){
                        sb.append(j).append("*").append(i);
                        sb.append("=").append(i*j).append(" ");
                    }
                    sb.append("<br/>");
                }
                return sb.toString();
            }
        %>
        <%=printTable()%>
    </div>
    <hr/>
    <div>
        <%!
            void printTable(JspWriter out) throws IOException{
                out.print("<h1>基于脚本</h1>");
                for (int i=1;i<10;i++){
                    for (int j=1;j<i;j++){
                        out.print(j + "*" + i + "=" + (i * j));
                        out.print("&nbsp;&nbsp;");
                    }
                    out.append("<br/>");
                }
            }
        %>
        <%
            printTable(out);
        %>
    </div>
</body>
</html>
