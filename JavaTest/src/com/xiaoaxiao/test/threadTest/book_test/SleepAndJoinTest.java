package com.xiaoaxiao.test.thread_test.book_test;

/**
 * Created by xiaoaxiao on 2019/7/16
 * Description: 验证sleep()不释放锁，join()释放锁
 *          1、sleep()情况下
 *            ThreadA中开启了ThreadB类的对象b，给b对象加锁，此时A持有对象b的锁
 *            然后将b启动后，ThreadA睡上6s，而主线程中只睡了1s，也就是1s后，ThreadC也被启动了
 *            看是否释放锁就看c被启动后，c调用对象b的同步方法和a对象调用b对象的后续语句的执行顺序
 *
 *            sleep()不释放锁：ThreadA在睡6s的过程中始终持有着对象b的锁，因此ThreadC只能等待
 *            ThreadA结束后才能调用对象b的同步方法
 *
 *          2、join()情况下
 *            join()释放锁：ThreadA中调用b.join()，在等待b.run()执行过程中，释放了锁，
 *            此时ThreadC开启后，获取到对象b的锁，直接执行了b的同步方法。
 *
 */

class ThreadA extends Thread{
    private ThreadB b;

    public ThreadA(ThreadB b) {
        super();
        this.b = b;
    }

    @Override
    public void run() {
        try {
            synchronized (b){
                b.start();

                // Thread.sleep()不释放锁
                Thread.sleep(6000);

                // Thread.join()释放了b的锁
                b.join();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadB extends Thread{
    @Override
    public void run() {
        try {
            System.out.println(" b run begin timer="+System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println(" b run end timer="+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void bService(){
        System.out.println("打印了bService timer="+System.currentTimeMillis());
    }
}

class ThreadC extends Thread{

    private ThreadB threadB;

    public ThreadC(ThreadB threadB) {
        super();
        this.threadB = threadB;
    }

    @Override
    public void run() {
        threadB.bService();
    }
}

public class SleepAndJoinTest {

    public static void main(String[] args) {
        try {
            ThreadB b = new ThreadB();
            ThreadA a = new ThreadA(b);
            a.start();
            Thread.sleep(1000);
            ThreadC c = new ThreadC(b);
            c.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
