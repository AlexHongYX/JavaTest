package com.xiaoaxiao.test.thread_test.book_test;

/**
 * Created by xiaoaxiao on 2019/7/16
 * Description: run()和start()不一样！！！
 */

class MyThread1 extends Thread{

    @Override
    public void run() {
        try {
            System.out.println("run threadName="+Thread.currentThread().getName()+" begin");
            Thread.sleep(2000);
            System.out.println("run threadName="+Thread.currentThread().getName()+" end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadTest2 {

    public static void main(String[] args) {
        MyThread1 mt = new MyThread1();
        System.out.println("begin ="+System.currentTimeMillis());
//        mt.run();
        mt.start();
        System.out.println("end ="+System.currentTimeMillis());
    }
}
