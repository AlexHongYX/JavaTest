package com.xiaoaxiao.myfirst.servlet.session_manage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaoaxiao on 2019/9/5
 * Description: 身份信息的修改——使用隐藏域——与PersonServlet搭配使用
 */
public class HiddenServlet extends HttpServlet {

    public static Map<String,Person> personMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        Person java = new Person("1001","Java",15);
        Person c = new Person("1002","C",20);
        Person python = new Person("1003","Python",25);

        personMap.put("1001",java);
        personMap.put("1002",c);
        personMap.put("1003",python);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>文件信息展示</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <table>\n" +
                "        <tr>\n" +
                "            <th>编号</th>\n" +
                "            <th>姓名</th>\n" +
                "            <th>年龄</th>\n" +
                "            <th>修改</th>\n" +
                "        </tr>");
        for (Map.Entry<String,Person> entry : personMap.entrySet()){
            Person person = entry.getValue();
            sb.append("<tr>\n" +
                    "            <td>");
            sb.append(person.getId());
            sb.append("</td>\n" +
                    "            <td>");
            sb.append(person.getName());
            sb.append("</td>\n" +
                    "            <td>");
            sb.append(person.getAge());
            sb.append("</td>\n" +
                    "            <td>");
            sb.append("<a href=\"person?id=").append(person.getId());
            sb.append("\">修改这个人的信息</a>");
            sb.append("</td>\n" +
                    "        </tr>");
        }
        sb.append("</table>\n" +
                "</body>\n" +
                "</html>");
        resp.getWriter().println(sb.toString());
        resp.getWriter().flush();
    }
}

class Person{
    private String id;
    private String name;
    private int age;

    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
