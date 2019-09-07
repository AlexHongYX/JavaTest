package com.xiaoaxiao.myfirst.servlet.session_manage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by xiaoaxiao on 2019/9/5
 * Description: 简单的Session测试
 */
public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        Object value = httpSession.getAttribute("name");
        if(value==null){
            httpSession.setAttribute("name","hello world"+ LocalDateTime.now());
        }

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        StringBuffer sb = new StringBuffer();
        sb.append("<a href='session'>").append("刷新").append("</a>");
        sb.append((String)value);
        resp.getWriter().println(sb.toString());
        resp.getWriter().flush();

    }
}
