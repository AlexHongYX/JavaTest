package com.xiaoaxiao.test.design_pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xiaoaxiao on 2019/9/5
 * Description: 动态代理——通过中介买房
 */

class CustomerB implements HouseBuyer{

    @Override
    public void buyHouse() {
        System.out.println("大家好，我是喜欢唱、跳、Rap、篮球的B，我在银行付钱买房");
    }
}

class CustomerC implements HouseBuyer{

    @Override
    public void buyHouse() {
        System.out.println("大家好，我是喜欢唱、跳、Rap、篮球的C，我在银行付钱买房");
    }
}

class DynProxyAgent implements InvocationHandler{

    // 代理类中持有被代理类的对象
    Object houseBuyer;

    // 代理类通过构造方法将被代理类的对象传入
    public DynProxyAgent(Object houseBuyer) {
        this.houseBuyer = houseBuyer;
    }

    public void beforeBuyHouse(){
        System.out.println("买房子前带着购房者坐车去游览小区再坐车去银行");
    }

    public void afterBuyHouse(){
        System.out.println("买房子后将购房者送至想去的位置");
    }

    // 无论调用被代理者中的哪个方法，都需要先进入代理类的invoke()，代理新增的逻辑就写在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.beforeBuyHouse();
        Object ret = method.invoke(this.houseBuyer,args);
        this.afterBuyHouse();
        return ret;
    }
}

/**
 * 动态代理工厂
 */
class DynProxyFactory{
    public static Object getProxy(Object target){
        InvocationHandler handler = new DynProxyAgent(target);
        // 被代理类必须实现接口
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),handler);
    }
}

public class DynProxyForBuyHouse {

    public static void main(String[] args) {
        HouseBuyer houseBuyerB = (HouseBuyer) DynProxyFactory.getProxy(new CustomerB());
        houseBuyerB.buyHouse();
        System.out.println("=============================================");
        HouseBuyer houseBuyerC = (HouseBuyer) DynProxyFactory.getProxy(new CustomerC());
        houseBuyerC.buyHouse();

        // $Proxy0 -> JDK 动态代理——运行时动态生成的类
        System.out.println(houseBuyerB.getClass().getName());
        System.out.println(houseBuyerC.getClass().getName());
    }
}
