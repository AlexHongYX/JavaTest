package com.xiaoaxiao.myfirst.listener;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiaoaxiao on 2019/9/17
 * Description: 请求级监听器(计算一个Request的时间)
 */
public class RequestCastTimeListener implements ServletRequestListener, ServletRequestAttributeListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("应用程序请求初始化啦");

        long startTime = System.currentTimeMillis();
        sre.getServletRequest().setAttribute("startTime",startTime);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("应用程序请求销毁啦");

        long endTime = System.currentTimeMillis();
        long startTime = (long) sre.getServletRequest().getAttribute("startTime");

        String url = ((HttpServletRequest)sre.getServletRequest()).getRequestURI();
        System.out.println("请求："+url+"花费了"+(endTime-startTime)+"ms");
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("应用程序请求的属性新增："+srae.getName()
                +"="+srae.getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("应用程序请求的属性移除："+srae.getName()
                +"="+srae.getValue());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("应用程序请求的属性更新："+srae.getName()
                +"="+srae.getValue());
    }
}
