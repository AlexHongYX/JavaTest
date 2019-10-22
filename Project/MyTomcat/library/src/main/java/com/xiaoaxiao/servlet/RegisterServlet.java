package com.xiaoaxiao.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xiaoaxiao on 2019/10/8
 * Description:
 */
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");
        if(password.equals(repassword)){
            System.out.println(username+":"+password);
        }else {
            System.out.println("两次密码不同！");
        }

        // 绝对路径写法
        resp.sendRedirect("/library/user/login.html");
    }
}
