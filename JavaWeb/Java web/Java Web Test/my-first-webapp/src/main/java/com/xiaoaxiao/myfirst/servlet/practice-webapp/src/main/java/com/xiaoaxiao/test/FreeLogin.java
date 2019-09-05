package com.xiaoaxiao.test;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by xiaoaxiao on 2019/9/5
 * Description: 3分钟内免密码登录
 */
public class FreeLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String username = null;
        String password = null;
        if (cookies!=null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("username")){
                    username = cookie.getValue();
                }else if(cookie.getName().equals("password")){
                    password = cookie.getValue();
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        if(username==null||password==null){
            sb.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>3分钟免密码登录</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <form action=\"login\" method=\"post\">\n" +
                    "        <input type=\"text\" name=\"username\" value=\"\"/><br/>\n" +
                    "        <input type=\"password\" name=\"password\" value=\"\"/><br/>\n" +
                    "        <input type=\"submit\" value=\"提交\"/>\n" +
                    "    </form>\n" +
                    "</body>\n" +
                    "</html>");
        }else {
            sb.append("已登录状态");
            sb.append("用户名为：").append(username);
            sb.append("密码为：").append(password);
        }
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.getWriter().println(sb.toString());
        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Cookie cookie1 = new Cookie("username",username);
        Cookie cookie2 = new Cookie("password",password);
        // 设置cookie的存活时间为180s
        cookie1.setMaxAge(180);
        cookie2.setMaxAge(180);
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.getWriter().write("登录成功");
        resp.getWriter().flush();
    }
}
