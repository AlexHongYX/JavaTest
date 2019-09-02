package com.xiaoaxiao.myfirst.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xiaoaxiao on 2019/9/2
 * Description: 由于ChanceServlet产生的线程安全问题，导致该Servlet实例被所有的用户共享
 *              因此使用类级别变量会被所有用户所共享，此时，使用ServletContext(上下文)
 *              为每一个用户提供一个属于该用户的非共享数据(从而使得每个用户使用的数据不会出现并发问题)
 */
public class ChancePlusServlet extends HttpServlet {

    private int chanceNumber;

    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("ChancePlusServlet init");
        String number = config.getInitParameter("chance_number");
        this.chanceNumber = Integer.parseInt(number);
        // 必须要在初始化的时候将context进行初始化(通过ServletConfig中的getServletContext()方法)
        this.context = config.getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        String name = req.getParameter("name");

        printWriter.append("<html>")
                .append("<head>")
                .append("<title>")
                .append("Servlet程序")
                .append("</title>")
                .append("</head>")
                .append("<body>");
//        // 先尝试取出key为name的值，若存在(之后直接取得)，若不存在(先设置再取得)
////        Object value = this.context.getAttribute(name);
////        // 如果number为null，说明是第一次设置值，将键值对(值为chanceNumber)加入到ServletContext中(上下文——公共区域)
////        if(value == null){
////            this.context.setAttribute(name,this.chanceNumber);
////        }
////        // 使用ServletContext类中的getAttribute()方法获取name对应的值(key-value)——此时name对应的value一定是存在的
////        int number = (int)this.context.getAttribute(name);
        // 使用ServletContext类中的getAttribute()方法获取name对应的值(key-value)
        Integer number = (Integer) this.context.getAttribute(name);
        if (number == null){

            // 必须先将chanceNumber赋给number，为之后number做判断打下基础
            number = this.chanceNumber;
            this.context.setAttribute(name,number);
        }
        if(number > 0){
            // 若number>0，将number-1的结果加入到name所对应的键值对中
            number = number-1;
            this.context.setAttribute(name,number);
            printWriter.append("<h1>").append(name).append("剩余").append(String.valueOf(number))
                    .append("次机会了").append("</h1>");
        }else {
            printWriter.append("<h2>").append("没机会了！").append("</h2>");
        }
        printWriter.append("</body>")
                .append("</html>");
        printWriter.flush();
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("ChancePlusServlet destroy");
    }
}
