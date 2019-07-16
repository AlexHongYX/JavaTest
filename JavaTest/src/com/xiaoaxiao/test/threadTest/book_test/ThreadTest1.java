package com.xiaoaxiao.test.thread_test.book_test;

/**
 * Created by xiaoaxiao on 2019/7/16
 * Description: 测试多线程不共享数据情况
 */

class MyThread extends Thread {

    private int count = 5;

    public MyThread(){
        System.out.println(Thread.currentThread().getName());
        System.out.println(this.getName());
    }

    @Override
    public void run() {
        while (count > 0){
            count--;
            System.out.println("由"+Thread.currentThread().getName()
                +"计算，count="+count);
//            System.out.println(this.getName());
        }
    }
}

public class ThreadTest1 {

    public static void main(String[] args) {
        MyThread mt1 = new MyThread();
//        MyThread mt2 = new MyThread();
//        MyThread mt3 = new MyThread();

        mt1.start();
//        mt2.start();
//        mt3.start();

    }
}
