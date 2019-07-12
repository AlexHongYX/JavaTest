package com.xiaoaxiao.test.thread_test.synchronized_test;

/**
 * Created by xiaoaxiao on 2019/7/12
 * Description: Synchronized是一个对象锁，只能防止多个线程同时执行“同一个对象”的同步端
 */

class Sync{
    public void test(){
        // 没锁住的原因是synchronized锁的是对象，而29行创建了多个对象
        synchronized (this){
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

class MyThread2 implements Runnable{

    private Sync sync = new Sync();

    @Override
    public void run() {
        // 每个线程都执行了run()方法，因此每一个线程对应了一个Sync对象
//        Sync sync = new Sync();
//        sync.test();
        sync.test();
    }
}

public class SynchronizedTest2 {

    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2();
        Thread thread1 = new Thread(myThread2,"线程A");
        Thread thread2 = new Thread(myThread2,"线程B");
        Thread thread3 = new Thread(myThread2,"线程C");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
