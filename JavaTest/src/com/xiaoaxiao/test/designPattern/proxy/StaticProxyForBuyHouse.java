package com.xiaoaxiao.test.design_pattern.proxy;

/**
 * Created by xiaoaxiao on 2019/9/5
 * Description: 静态（普通）代理模式——通过中介买房
 */

interface HouseBuyer{
    void buyHouse();
}

/**
 * 真实实例类(被代理类)
 */
class CustomerA implements HouseBuyer{

    @Override
    public void buyHouse() {
        System.out.println("在银行付钱买房");
    }
}

/**
 * 代理类
 */
class Agent implements HouseBuyer{

    // 代理类中持有被代理类的对象
    HouseBuyer houseBuyer;

    // 代理类通过构造方法将被代理类的对象传入
    public Agent(HouseBuyer houseBuyer) {
        this.houseBuyer = houseBuyer;
    }

    public void beforeBuyHouse(){
        System.out.println("买房子前带着购房者坐车去游览小区再坐车去银行");
    }

    // 代理类实现接口的方法==调用被代理类实现该接口的方法(真实业务)
    //          +代理类中定义的其他用于辅助真实业务操作的方法(辅助方法)
    @Override
    public void buyHouse() {
        beforeBuyHouse();
        this.houseBuyer.buyHouse();
        afterBuyHouse();
    }

    public void afterBuyHouse(){
        System.out.println("买房子后将购房者送至想去的位置");
    }
}

/**
 * 设置静态代理工厂，返回代理类实例
 */
class ProxyFactory{
    // 工厂方法一般都是静态的
    public static HouseBuyer getProxy(){
        return new Agent(new CustomerA());
    }
}

public class StaticProxyForBuyHouse {

    public static void main(String[] args) {
        HouseBuyer houseBuyer = ProxyFactory.getProxy();
        houseBuyer.buyHouse();
    }
}
