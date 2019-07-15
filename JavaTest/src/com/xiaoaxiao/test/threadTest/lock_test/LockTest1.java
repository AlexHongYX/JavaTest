package com.xiaoaxiao.test.thread_test.lock_test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiaoaxiao on 2019/7/15
 * Description: 测试一下Lock的使用流程
 */

class MyThread1 implements Runnable{

    private int ticket = 500;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        for (int i=0;i<500;i++){
            try {
                lock.lock();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(ticket>0){
                    System.out.println(Thread.currentThread().getName()
                            +"还剩"+ticket--+"票");
                }
            }finally {
                lock.unlock();
            }
        }

    }
}

public class LockTest1 {

    public static void main(String[] args) {
        MyThread1 mt = new MyThread1();

        Thread thread1 = new Thread(mt,"黄牛A");
        Thread thread2 = new Thread(mt,"黄牛B");
        Thread thread3 = new Thread(mt,"黄牛C");

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
