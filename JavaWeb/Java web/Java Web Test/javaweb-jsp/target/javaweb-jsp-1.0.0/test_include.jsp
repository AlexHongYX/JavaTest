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
    <title>测试include</title>
</head>
<body>
    <%@ include file="segments/header.jsp"%>
    <%--<%@include file="segments/footer.jsp"%>--%>
    <div>
        body内容
    </div>
    <jsp:include page="segments/footer.jsp" flush="true"/>
</body>
</html>
