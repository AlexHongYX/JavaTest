package com.xiaoaxiao.test.thread_test.thread_basic_test;

/**
 * Created by xiaoaxiao on 2019/7/12
 * Description: 测试thread常用的方法——sleep，yield
 */

class MyRunnable2 implements Runnable{

    @Override
    public void run() {
        for(int i=0;i<3;i++){
            System.out.println(Thread.currentThread().getName());
            // sleep()
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // yield()
//            Thread.yield();

        }
    }
}

public class threadMethodTest1 {

    public static void main(String[] args) {
        MyRunnable2 myRunnable2 = new MyRunnable2();
        Thread thread1 = new Thread(myRunnable2,"A");
//        Thread thread2 = new Thread(myRunnable2,"hello");
        Thread thread2 = new Thread(myRunnable2,"B");
        Thread thread3 = new Thread(myRunnable2,"C");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
