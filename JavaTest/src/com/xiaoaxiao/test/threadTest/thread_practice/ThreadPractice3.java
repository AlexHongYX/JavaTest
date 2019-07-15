package com.xiaoaxiao.test.thread_test.thread_practice;

/**
 * Created by xiaoaxiao on 2019/7/15
 * Description: 写3个线程，打印如下（利用SB，线程安全）
 * *Thread-0
 * *Thread-0@Thread-1
 * *Thread-0@Thread-1#Thread-2
 * *Thread-0@Thread-1#Thread-2*Thread-0
 * *Thread-0@Thread-1#Thread-2*Thread-0@Thread-1
 * Thread-0@Thread-1#Thread-2*Thread-0@Thread-1#Thread-2
 * ...
 */

class Print2 {
    private int flag = 1;
    private int count = 0;
//    private StringBuffer sb = new StringBuffer();
    private StringBuilder sb = new StringBuilder();

    public int getCount() {
        return count;
    }

    public synchronized void printThread0() {
        while (flag != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        sb.append("*").append(Thread.currentThread().getName());
        System.out.println(sb);
//        System.out.println("*"+Thread.currentThread().getName());

        flag = 2;
        count++;
        notifyAll();
    }

    public synchronized void printThread1() {
        while (flag != 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        sb.append("@").append(Thread.currentThread().getName());
        System.out.println(sb.toString());

//        System.out.println("@"+Thread.currentThread().getName());

        flag = 3;
        count++;
        notifyAll();
    }

    public synchronized void printThread2() {
        while (flag != 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        sb.append("#").append(Thread.currentThread().getName());
        System.out.println(sb.toString());

//        System.out.println("#"+Thread.currentThread().getName());

        flag = 1;
        count++;
        notifyAll();
    }
}

class MyThread4 implements Runnable {

    Print2 print2 = new Print2();

    @Override
    public void run() {
        while (this.print2.getCount() < 31) {
            if (Thread.currentThread().getName().equals("Thread-0")) {
                print2.printThread0();
            } else if (Thread.currentThread().getName().equals("Thread-1")) {
                print2.printThread1();
            } else {
                print2.printThread2();
            }
        }
    }
}

public class ThreadPractice3 {

    public static void main(String[] args) {
        MyThread4 mt = new MyThread4();
        Thread thread0 = new Thread(mt);
        Thread thread1 = new Thread(mt);
        Thread thread2 = new Thread(mt);

        thread0.start();
        thread1.start();
        thread2.start();
    }
}
