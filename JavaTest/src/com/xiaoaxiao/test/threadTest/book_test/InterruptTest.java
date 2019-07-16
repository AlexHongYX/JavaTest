package com.xiaoaxiao.test.thread_test.book_test;

/**
 * Created by xiaoaxiao on 2019/7/16
 * Description: sleep()对Interrupt()的影响
 *              无论是在sleep()过程中，interrupt()
 *              还是在interrupt()过程中，sleep()
 *              都会抛出InterruptedException异常，并将interrupt状态置为false
 */

class MyThread2 extends Thread{

    @Override
    public void run() {
        super.run();

        try {
            for(int i=0;i<1000000;i++){
                System.out.println("i="+(i+1));
            }
            System.out.println("run begin");
            Thread.sleep(200000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.out.println("先停止，再遇到sleep，interrupt状态为："+this.isInterrupted());
            e.printStackTrace();
        }
    }
}

public class InterruptTest {

    public static void main(String[] args) {
        MyThread2 mt = new MyThread2();

        mt.start();
        mt.interrupt();
        System.out.println("end!");
    }
}
