package com.xiaoaxiao.test.thread_test.synchronized_test;

/**
 * Created by xiaoaxiao on 2019/7/12
 * Description: Synchronized全局锁
 */

class Sync2{
    public void test(){
        // 通过锁住该类.class，设置全局锁
        synchronized (Sync2.class){
            System.out.println("test方法开始，当前线程为"
                    +Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test方法结束，当前线程为"
                    +Thread.currentThread().getName());
        }
    }
}

class MyThread3 implements Runnable{


    @Override
    public void run() {

        Sync2 sync = new Sync2();
        sync.test();
    }
}

public class SynchronizedTest3 {

    public static void main(String[] args) {
        MyThread3 myThread3 = new MyThread3();
        Thread thread1 = new Thread(myThread3,"线程A");
        Thread thread2 = new Thread(myThread3,"线程B");
        Thread thread3 = new Thread(myThread3,"线程C");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
