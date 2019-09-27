package com.xiaoaxiao.test.thread_test.thread_basic_test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiaoaxiao on 2019/7/12
 * Description: 测试thread常用的方法——join
 */

class MyRunnable3 implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println("主线程睡眠前时间");
            threadMethodTest2.printTime();
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
            System.out.println("睡眠结束时间");
            threadMethodTest2.printTime();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class threadMethodTest2 {

    public static void main(String[] args) throws InterruptedException {
        MyRunnable3 myRunnable3 = new MyRunnable3();
        Thread threadA = new Thread(myRunnable3,"子线程A");
        System.out.println("代码开始");
        threadA.start();
        // 调用子线程的join方法，当主线程执行到这一步了，
        // 一定会等到子线程中所有内容全部执行完，主线程才会继续往下执行
        threadA.join();
        System.out.println(Thread.currentThread().getName());
        System.out.println("代码结束");
    }

    public static void printTime(){
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        System.out.println(time);
    }
}
