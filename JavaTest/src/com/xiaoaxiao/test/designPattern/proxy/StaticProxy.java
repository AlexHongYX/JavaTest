package com.xiaoaxiao.test.design_pattern.proxy;

import java.lang.reflect.InvocationHandler;

/**
 * Created by xiaoaxiao on 2019/7/24
 * Description: 静态（普通）代理模式
 */

interface IStaticSubject{

    void eat();
}


class RealStaticSubject implements IStaticSubject{
    @Override
    public void eat() {
        System.out.println("吃大餐");
    }
}

class ProxyStaticSubject implements IStaticSubject{

    private IStaticSubject iStaticSubject;

    public ProxyStaticSubject(IStaticSubject iStaticSubject) {
        this.iStaticSubject = iStaticSubject;
    }

    private void beforeEat(){
        System.out.println("洗手");
    }

    private void afterEat(){
        System.out.println("擦嘴");
    }

    @Override
    public void eat() {
        beforeEat();
        this.iStaticSubject.eat();
        afterEat();
    }

}

public class StaticProxy {

    public static void main(String[] args) {

        IStaticSubject iStaticSubject = new ProxyStaticSubject(new RealStaticSubject());

        iStaticSubject.eat();
    }
}
