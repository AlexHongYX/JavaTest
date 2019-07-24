package com.xiaoaxiao.test.design_pattern.SingletonAndMultiton;

/**
 * Created by xiaoaxiao on 2019/7/11
 * Description: 单例模式——饿汉式
 */

class Singleton1 {

    // 直接实例化（饿汉式）
    private static final Singleton1 SINGLETON_1 = new Singleton1();

    // 构造方法私有
    private Singleton1() {
    }

    // 静态方法获得单例对象（外部连对象都不能创建，不能用普通方法）
    public static Singleton1 getInstance() {
        return SINGLETON_1;
    }
}

public class SingletonTest1 {

    public static void main(String[] args) {
        Singleton1 singleton1 = Singleton1.getInstance();
        Singleton1 singleton12 = Singleton1.getInstance();

        // singleton1==singleton2为true，说明Singleton是一个单例模式
        System.out.println(singleton1 == singleton12);
    }
}
