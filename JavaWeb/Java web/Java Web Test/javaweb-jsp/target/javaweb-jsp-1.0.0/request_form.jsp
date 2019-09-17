<%--
  Created by IntelliJ IDEA.
  User: xiaoaxiao
  Date: 2019/9/17
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户选择技能</title>
</head>
<body>
    <h2>用户选择技能</h2>
    <form id="skill_form" method="get" action="request_handle.jsp">
        <table>
            <tr>
                <td>
                    用户名
                </td>
                <td>
                    <input type="text" name="username" id="username" value="" placeholder="请输入用户名"/>
                </td>
            </tr>
            <tr>
                <td>技能</td>
                <td>
                    <!-- 多选框 -->
                    <input type="checkbox" name="skill" value="Java语言"/> Java语言
                    <input type="checkbox" name="skill" value="C++语言"/>  C++语言
                    <input type="checkbox" name="skill" value="Python语言"/> Python语言
                    <input type="checkbox" name="skill" value="PHP语言"/> PHP语言
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="提交"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
