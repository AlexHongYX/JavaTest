package com.xiaoaxiao.myfirst.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Created by xiaoaxiao on 2019/9/7
 * Description: 记录每次访问的url
 *          1、指定初始化参数，记录的前缀，记录的文件名
 *          2、每个请求的URI记录到文件中
 *          3、web.xml配置(Filter，拦截的地址，初始化参数)
 *
 */
public class LoginFilter implements Filter {

    private String prefix = "";
    private String loggingFile = "";
    // 定义一个字符打印流(输出流)
    private PrintWriter printWriter;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.prefix = filterConfig.getInitParameter("logging_prefix");
        this.loggingFile = filterConfig.getInitParameter("logging_filename");
        // 获取当前Web程序打包后的根目录(编译后的文件my-first-webapp-1.0.0为程序的根目录)
        String webAppRoot = filterConfig.getServletContext().getRealPath("/");
        try {
            this.printWriter = new PrintWriter(new File(webAppRoot,this.loggingFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Logging Filter init called.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Logging Filter doFilter called.");

        // **注意URL与URI的区别**

        String url = ((HttpServletRequest) request).getRequestURI();
        this.printWriter.println(this.prefix+ LocalDateTime.now()+":"+url);
        this.printWriter.flush();

        // 该过滤器执行完，需要执行链的doFilter()，进入下一个过滤器或是Servlet(当前是最后一个过滤器)
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("Logging Filter destroy called.");
    }
}
