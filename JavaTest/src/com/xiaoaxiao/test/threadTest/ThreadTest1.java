package com.xiaoaxiao.test.thread_test;

/**
 * Created by xiaoaxiao on 2019/7/11
 * Description: 继承Thread类实现多线程
 */

class MyThread1 extends Thread{
    private String title;

    public MyThread1(String title){
        this.title = title;
    }

    @Override
    public void run() {
        for (int i = 0;i<10;i++) {
            System.out.println(title+":"+i);
        }
    }
}

public class ThreadTest1 {
    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1("线程1");
        MyThread1 myThread11 = new MyThread1("线程2");
        MyThread1 myThread111 = new MyThread1("线程3");
        myThread1.start();
        myThread11.start();
        myThread111.start();
    }
}
