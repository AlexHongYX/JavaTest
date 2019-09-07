package com.xiaoaxiao.myfirst.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by xiaoaxiao on 2019/9/7
 * Description: 检测多个Filter共同使用时的流程，对应CharacterEncodingFilter
 */
public class LoginCheckFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Login Check Before");
        chain.doFilter(request, response);
        System.out.println("Login Check After");
    }

    @Override
    public void destroy() {
        //dp nothing
    }
}
