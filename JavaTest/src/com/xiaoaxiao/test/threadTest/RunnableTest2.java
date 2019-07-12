package com.xiaoaxiao.test.thread_test;

/**
 * Created by xiaoaxiao on 2019/7/11
 * Description: 通过实现Runnable接口的方式实现多线程更能体现出“共享”的概念
 */

class MyRunnable implements Runnable{

    private String title;
    private Integer ticket = 10;

    public MyRunnable(String title){
        this.title = title;
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++) {
            System.out.println(this.title+"卖了一张票，还剩"+(ticket--)+"张票");
        }
    }
}

public class RunnableTest2 {

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable("黄牛");
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);
        Thread thread3 = new Thread(myRunnable);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
