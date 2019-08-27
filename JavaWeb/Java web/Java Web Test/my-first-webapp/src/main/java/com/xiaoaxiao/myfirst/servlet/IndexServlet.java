package com.xiaoaxiao.myfirst.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Created by xiaoaxiao on 2019/8/27
 * Description:
 */
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.append("<html>")
                    .append("<head>")
                        .append("<title>")
                            .append("Servlet程序")
                        .append("</title>")
                    .append("</head>")
                    .append("<body>")
                        .append("<h1>")
                            .append("Hello World!!")

                        .append("</h1>")
                    .append("</body>")
                .append("</html>");
        printWriter.flush();
    }
}
