package com.xiaoaxiao.myfirst.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by xiaoaxiao on 2019/9/17
 * Description: 会话级监听器(Session)
 */
public class SessionCountListener implements HttpSessionListener, ServletContextListener, HttpSessionAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 先实现ServletContextListener
        // 应用程序启动时，默认的session数量是0——设置全局变量
        ServletContext context = sce.getServletContext();
        context.setAttribute("session_count",0);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 应用程序停止时，移除session_count属性
        ServletContext context = sce.getServletContext();
        context.removeAttribute("session_count");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        // 当应用程序建立一个会话(session)，session_out+1
        Integer sessionCount = Integer.parseInt(String.valueOf(
                context.getAttribute("session_count")));
        sessionCount++;
        context.setAttribute("session_count",sessionCount);
        System.out.println("HttpSession 创建啦");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        // 当应用程序关闭一个会话(session)，session_out+1
        Integer sessionCount = Integer.parseInt(String.valueOf(
                context.getAttribute("session_count")));
        sessionCount--;
        context.setAttribute("session_count",sessionCount);
        System.out.println("HttpSession 销毁啦");
    }


    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("应用程序会话的属性新增："+event.getName()
            +"="+event.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("应用程序会话的属性移除："+event.getName()
                +"="+event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("应用程序会话的属性替换："+event.getName()
                +"="+event.getValue());
    }
}
