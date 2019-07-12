package com.xiaoaxiao.test.thread_test.synchronized_test;

/**
 * Created by xiaoaxiao on 2019/7/12
 * Description: Synchronize面试题
 *      线程1进入testA()，且线程1仍在执行时，线程2能否进入testB()？
 *      绝不可能
 *      无论是多个线程对应一个对象还是一个线程对应一个对象
 *      一旦第一个线程的对象进入到testA中还未出来，这个线程就被锁住了，不可能再去调用testB()
 */

class Sync3{

    public synchronized void testA(){
        if(Thread.currentThread().getName().equals("A")){
            while (true){
                System.out.println(Thread.currentThread().getName()+":testA()根本停不下来");
            }
        }
    }

    public synchronized void testB(){
        if(Thread.currentThread().getName().equals("B")){
            System.out.println(Thread.currentThread().getName()+":线程B打印此方法");
        }
    }
}

class MyThread4 implements Runnable{

//    private Sync3 sync3;
//
//    public MyThread4(Sync3 sync3){
//        this.sync3 = sync3;
//    }

    @Override
    public void run() {
//        this.sync3.testA();
//        this.sync3.testB();
        Sync3 sync3 = new Sync3();
        sync3.testA();
        sync3.testB();
    }
}

public class SynchronizeInterview {
    public static void main(String[] args) throws InterruptedException {
//        Sync3 sync3 = new Sync3();
//        MyThread4 myThread4 = new MyThread4(sync3);
        MyThread4 myThread4 = new MyThread4();

        Thread thread1 = new Thread(myThread4,"A");
        Thread thread2 = new Thread(myThread4,"B");

        thread1.start();

        Thread.sleep(1000);

        thread2.start();
    }
}

