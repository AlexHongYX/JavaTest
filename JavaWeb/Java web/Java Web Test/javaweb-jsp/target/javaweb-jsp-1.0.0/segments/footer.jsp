<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>
    这是一个尾部信息，当前访问时间
    <%
        LocalDateTime dateTime = LocalDateTime.now();
        out.print(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    %>
</h1>