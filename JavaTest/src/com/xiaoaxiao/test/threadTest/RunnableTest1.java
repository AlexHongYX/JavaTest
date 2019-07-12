package com.xiaoaxiao.test.thread_test;

/**
 * Created by xiaoaxiao on 2019/7/11
 * Description: 实现Runnable接口实现多线程
 */

class MyThread2 implements Runnable{

    private String title;

    public MyThread2(String title){
        this.title = title;
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++) {
            System.out.println(this.title+":"+i);
        }
    }
}

public class RunnableTest1 {

    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2("线程1");
        MyThread2 myThread22 = new MyThread2("线程2");
        MyThread2 myThread222 = new MyThread2("线程3");
        new Thread(myThread2).start();
        new Thread(myThread22).start();
        new Thread(myThread222).start();
    }
}
