package com.xiaoaxiao.myfirst.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xiaoaxiao on 2019/9/3
 * Description: 表单提交处理逻辑
 */
public class FormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>表单提交</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form method=\"post\" action=\"FormServlet\">\n" +
                "        <input id=\"name\" type=\"text\" name=\"name\" placeholder=\"请输入姓名\"\n" +
                "            value=\"\"/>\n" +
                "        <input id=\"submit\" type=\"submit\" value=\"提交\"/>\n" +
                "    </form>\n" +
                "</body>\n" +
                "</html>");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter pw = resp.getWriter();
        pw.println(sb.toString());
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>表单提交</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>欢迎");
        pw.append(name)
                .append("</h1>\n" +
                        "</body>\n" +
                        "</html>");
        pw.flush();
    }
}
