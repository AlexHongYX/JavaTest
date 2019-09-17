package com.xiaoaxiao.myfirst.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by xiaoaxiao on 2019/9/17
 * Description: 应用级监听
 */
public class AppServletContextListener implements ServletContextListener, ServletContextAttributeListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("应用程序的ServletContext初始化啦");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("应用程序的ServletContext销毁啦");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println("应用程序的ServletContext的属性新增："+event.getName()+"="+event.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println("应用程序的ServletContext的属性移除："+event.getName()+"="+event.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("应用程序的ServletContext的属性替换："+event.getName()+"="+event.getValue());
    }
}
