package com.xiaoaxiao.test.other_test;

/**
 * 三个线程A、B、C分别打印字符串A、B、C
 * 要求：循环打印十次
 * 打印结果为：
 * ABC
 * ABC
 * ...
 * ABC
 * 以上共十次
 */
public class ThreadTest {
    public static boolean firstFinshed;
    public static boolean secondFinshed;

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (ThreadTest.class) {
                        System.out.print("A");
                        firstFinshed = true;
                        ThreadTest.class.notifyAll();
                    }
                }
            });
            t1.start();


            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        synchronized (ThreadTest.class) {
                            while (!firstFinshed) {
                                t1.wait();
                            }
                            System.out.print("B");
                            secondFinshed = true;
                            ThreadTest.class.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.start();


            Thread t3 = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        synchronized (ThreadTest.class) {
                            while (!secondFinshed) {
                                t2.wait();
                            }
                            System.out.print("C");
                            System.out.println();
                            ThreadTest.class.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t3.start();
        }
    }
}

