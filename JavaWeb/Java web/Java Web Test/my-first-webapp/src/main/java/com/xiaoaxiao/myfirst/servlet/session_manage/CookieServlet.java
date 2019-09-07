package com.xiaoaxiao.myfirst.servlet.session_manage;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoaxiao on 2019/9/5
 * Description: 省市区联动——通过URL的增减
 */
public class CookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        Cookie[] cookies = req.getCookies();
        if(cookies!=null){
            String pro = null;
            String city = null;
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("pro")){
                    pro = cookie.getValue();
                    continue;
                }
                if (cookie.getName().equals("city")){
                    city = cookie.getValue();
                    continue;
                }
            }
            pw.append("<h1>").append(pro).append("</h1>");
            pw.append("<h2>").append(city).append("</h2>");
        }else {
            Cookie proCookie = new Cookie("pro","陕西");
            resp.addCookie(proCookie);
            Cookie cityCookie = new Cookie("city","西安");
            resp.addCookie(cityCookie);
            pw.append("<h1>初始化中...请刷新</h1>");
        }
        pw.flush();

    }
}
