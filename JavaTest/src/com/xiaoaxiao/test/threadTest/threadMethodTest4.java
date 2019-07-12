package com.xiaoaxiao.test.thread_test;

/**
 * Created by xiaoaxiao on 2019/7/12
 * Description: 测试thread常用的方法——线程停止-interrupt()
 */

class MyRunnable5 implements Runnable{

    @Override
    public void run() {

        while (true){

            try {

                boolean bool = Thread.currentThread().isInterrupted();
                System.out.println("非阻塞状态，线程状态为："+bool);
                Thread.sleep(100);

            } catch (InterruptedException e) {
                // 当5s后interrupt()给线程设置⼀个为true的中断标志
                // 再次遇到是wait、sleep以及join 三个⽅法引起的阻塞，
                // 会将线程的中断标志重新设置为false，并抛出⼀个 InterruptedException
                boolean bool = Thread.currentThread().isInterrupted();
                System.out.println("退出了"+bool);
                return;
            }
        }
    }
}

public class threadMethodTest4 {

    public static void main(String[] args) throws InterruptedException {
        MyRunnable5 myRunnable5 = new MyRunnable5();
        Thread thread = new Thread(myRunnable5,"子线程A");
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
        thread.join();
        System.out.println("代码结束");
    }
}
