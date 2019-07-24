package com.xiaoaxiao.test.design_pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xiaoaxiao on 2019/7/24
 * Description: 动态代理
 */

interface ISubject{
    public void eat(String msg,int num);

    public void sleep(String msg);
}

class RealSubject implements ISubject{

    @Override
    public void eat(String msg,int num) {
        System.out.println("吃"+num+"分量的"+msg);
    }

    @Override
    public void sleep(String msg) {
        System.out.println(msg+"sleep now");
    }
}

class ProxySubject implements InvocationHandler{

    private Object realObject;

    public Object bind(Object realObject){
        this.realObject = realObject;
        return Proxy.newProxyInstance(realObject.getClass().getClassLoader(),
                realObject.getClass().getInterfaces(),this);
    }

    public void preHandle(){
        System.out.println("方法前");
    }

    public void afterHandle(){
        System.out.println("方法后");
    }

    /**
     * 由Proxy生成的代理类中的*所有方法*被调用时都会通知和转发内部的InvocationHandler实现类，
     * 并由InvocationHandler的invoke()方法决定怎样处理传过来的方法调用
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.preHandle();
        Object ret = method.invoke(this.realObject,args);
        this.afterHandle();
        return ret;
    }
}

public class DynamicProxy {

    public static void main(String[] args) {
        ISubject iSubject = (ISubject) new ProxySubject().bind(new RealSubject());
        iSubject.eat("土豆",10);
        iSubject.sleep("wo ");
    }
}
