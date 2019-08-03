package com.xiaoaxiao.test.design_pattern.SingletonAndMultiton;

/**
 * Created by xiaoaxiao on 2019/8/3
 * Description: 懒汉式单例——双重检查锁+volatile禁止指令重排机制
 *
 *          1、双重加锁的意义：因为synchronized锁是加在if语句中，
 *              多线程的情况下，多个线程都可以到达if语句中，如果不在锁中再次进行判断
 *              的话，会产生多个对象从而破坏单例模式（synchronized只是保证了同一时间
 *              只能有一个线程进入代码块，因此在里面还得再次进行判断）
 *          2、设置volatile变量的意义：singleton = new Singleton()其实是三步操作
 * 	                ①在堆上分配空间
 * 	                ②属性初始化
 *                  ③引用指向对象
 *              由于JVM存在指令重排序的隐患，②③的顺序是不能被保证的，如果一个对象已经
 *              产生，但没有被初始化，就会报错，因此使用volatile禁止指令的重排序。
 *              从而达到，对象的创建一定会先初始化再产生对象。
 */

class Singleton{
    // volatile保证产生对象的完整性
    private static volatile Singleton singleton;
    private Singleton(){}
    public static Singleton getInstance(){
        if (singleton == null){     //singleton checked
            synchronized (Singleton.class){
                if (singleton == null){     //double checked
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}

public class DoubleCheckedSingleton {

    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        System.out.println(singleton1==singleton2);
    }

}
