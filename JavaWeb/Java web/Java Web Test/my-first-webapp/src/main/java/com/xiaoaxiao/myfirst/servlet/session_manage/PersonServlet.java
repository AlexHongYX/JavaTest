package com.xiaoaxiao.myfirst.servlet.session_manage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xiaoaxiao on 2019/9/5
 * Description: 修改个人信息——使用隐藏域——与HiddenServlet搭配使用
 */
public class PersonServlet extends HttpServlet {

    // 表单以post的格式发给person(url地址)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        StringBuilder sb = new StringBuilder();

        String id = req.getParameter("id");
        if(id == null){
            sb.append("id不能为空");
        }else {
            Person person = HiddenServlet.personMap.get(id);
            if (person==null){
                sb.append("找不到id为"+id+"的人");
            }else {
                sb.append("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>文件信息展示</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <form action=\"person\" method=\"post\">\n" +
                        "        <input type=\"hidden\" name=\"id\" value=\"");
                sb.append(person.getId());
                sb.append("\"/>\n" +
                        "<h1>用户编号：");
                sb.append(person.getId());
                sb.append("</h1>"+
                "        <input type=\"text\" name=\"name\" value=\"");
                sb.append(person.getName());
                sb.append("\"/>\n" +
                        "        <input type=\"text\" name=\"age\" value=\"");
                sb.append(person.getAge());
                sb.append("\"/>\n" +
                        "        <input type=\"submit\" value=\"提交\">\n" +
                        "    </form>\n" +
                        "</body>\n" +
                        "</html>");
            }
        }
        resp.getWriter().println(sb.toString());
        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        StringBuilder sb = new StringBuilder();

        String id = req.getParameter("id");
        if (id == null){
            sb.append("id不能为空");
        }else {
            Person person = HiddenServlet.personMap.get(id);
            if(person==null){
                sb.append("找不到id为"+id+"的人");
            }else {
                String name = req.getParameter("name");
                String age = req.getParameter("age");
                person.setName(name);
                person.setAge(Integer.parseInt(age));
                sb.append("<h2>修改成功</h2>");
                sb.append("<a href=\"hidden\">返回主页</a>");
            }
        }

        resp.getWriter().println(sb.toString());
        resp.getWriter().flush();
    }
}
