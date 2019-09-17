<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: xiaoaxiao
  Date: 2019/9/17
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>第一个jsp页面</title>
</head>
<body>
    <h1>欢迎来到JSP页面</h1>
    <%
        String dateTime = LocalDateTime.now().toString();
        out.println(dateTime);
    %>
    <!-- HTML注释 -->
    <%-- JSP注释 --%>
    <div>
        <%!
            String name = "Mac";
            int add(int a,int b){
                return a+b;
            }
        %>

        <p>名称：<%=name%></p>
        <p>计算求和(1+2)：<%=add(1,2)%></p>
    </div>
    <div>
        <table>
            <thead>
                <tr>
                    <th>编码</th>
                    <th>城市</th>
                    <th>景点</th>
                </tr>
            </thead>
            <tbody>
                <%!
                    // 声明变量
                    Map<String, List<String>> map = new HashMap<>();
                %>
                <%
                    map.put("西安", Arrays.asList("大雁塔", "兵马俑"));
                    map.put("宝鸡", Arrays.asList("太白山", "法门寺"));
                    map.put("咸阳", Arrays.asList("乾陵", "茂陵"));
                    int index = 0;
                    for (Map.Entry<String,List<String>> entry:  map.entrySet()){
                        String city = entry.getKey();
                        List<String> spots = entry.getValue();
                        for (String spot:spots){
                            index++;
                %>
                <tr>
                    <td><%=index%></td>
                    <td><%=city%></td>
                    <td><%=spot%></td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
