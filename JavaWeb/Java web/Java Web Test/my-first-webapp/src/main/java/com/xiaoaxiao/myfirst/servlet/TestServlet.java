package com.xiaoaxiao.myfirst.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xiaoaxiao on 2019/9/3
 * Description: 测试直接访问url地址是get/post请求
 */
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>测试</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>This is a Get request</h1>\n" +
                "</body>\n" +
                "</html>");
        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>测试</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>This is a Post request</h1>\n" +
                "</body>\n" +
                "</html>");
        resp.getWriter().flush();
    }
}
