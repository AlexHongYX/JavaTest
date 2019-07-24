package com.xiaoaxiao.test.design_pattern.SingletonAndMultiton;

/**
 * Created by xiaoaxiao on 2019/7/11
 * Description: 单例模式——懒汉式
 */

class Singleton2{

    // 只声明，不实例化（懒汉式）
    private static Singleton2 singleton2 = null;

    // 构造方法私有
    private Singleton2(){}

    // 静态方法获得单例对象（外部连对象都不能创建，不能用普通方法）
    public static Singleton2 getInstance(){
        if(singleton2 == null){
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}

public class SingletonTest2 {

    public static void main(String[] args) {
        Singleton1 singleton1 = Singleton1.getInstance();
        Singleton1 singleton12 = Singleton1.getInstance();

        // singleton1==singleton2为true，说明Singleton是一个单例模式
        System.out.println(singleton1== singleton12);
    }
}
