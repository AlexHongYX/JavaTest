package com.xiaoaxiao.test.design_pattern.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by xiaoaxiao on 2019/9/7
 * Description: 代理类不需要实现接口也能实现 动态生成代理类——CgLib
 *      1、实现一个MethodInterceptor接口，用于增强业务逻辑
 *      2、Enhancer
 *          2.1 业务类
 *          2.2 业务类增强的逻辑
 *          2.3 调用create()
 *     查看一下最后的代理类的类型（EnhancerByCGLIB）
 */

class Message{
    public void send(){
        System.out.println("send a message");
    }
}

class ClassProxy implements MethodInterceptor{

    // 接收真实业务类
    private Object target;

    public ClassProxy(Object target) {
        this.target = target;
    }

    private void beforeMethod(){
        System.out.println("before send");
    }

    private void afterMethod(){
        System.out.println("after send");
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
          beforeMethod();
          Object ret = method.invoke(this.target,objects);
          afterMethod();
          return ret;
    }
}

class CgLibFactory{
    public static Object getCgLib(Object obj){
        // 负责代理对象的代理处理类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        // 代理对象，以上就动态配置好了类之间的代理关系
        enhancer.setCallback(new ClassProxy(obj));
        return enhancer.create();
    }
}

public class CgLibProxy {
    public static void main(String[] args) {
        Message msg = new Message();
        Message tmp = (Message) CgLibFactory.getCgLib(msg);
        tmp.send();
    }
}
