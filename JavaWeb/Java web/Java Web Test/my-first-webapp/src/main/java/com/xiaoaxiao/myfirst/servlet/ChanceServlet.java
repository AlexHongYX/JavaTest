package com.xiaoaxiao.myfirst.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xiaoaxiao on 2019/8/29
 * Description: Servlet尽量保证线程安全，不建议使用类级别变量，除非它们是只读的或者
 *              支持并发修改的
 */
public class ChanceServlet extends HttpServlet {

    private int chanceNumber;

//    @Override
//    public void init() throws ServletException {
//        super.init();
//        this.chanceNumber = 10;
//    }

    /**
     * Tomcat会解析web.xml init-param ServletConfig
     */
    // 带有初始化参数的init()方法，初始化参数的配置在web.xml中
    @Override
    public void init(ServletConfig config) throws ServletException {
        // 此时的super.init不能省
        super.init(config);
        String chanceNumberStr = config.getInitParameter("chance_number");
        this.chanceNumber = Integer.parseInt(chanceNumberStr);
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
                if(chanceNumber > 0){
                    chanceNumber = chanceNumber-1;
                    printWriter.append("<h1>").append(name).append("剩余").append(String.valueOf(chanceNumber))
                            .append("次机会了").append("</h1>");
                }else {
                    printWriter.append("<h2>").append("没机会了！").append("</h2>");
                }
        printWriter.append("</body>")
                .append("</html>");
        printWriter.flush();
    }
}
