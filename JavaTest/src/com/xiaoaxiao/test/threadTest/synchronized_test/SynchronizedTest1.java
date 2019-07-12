package com.xiaoaxiao.test.thread_test.synchronized_test;

/**
 * Created by xiaoaxiao on 2019/7/12
 * Description: Synchronized同步代码块与同步方法
 */

class MyThread1 implements Runnable{

    private int ticket = 100;

    @Override
    public void run() {
        for(int i=0;i<100;i++){

//            // 同步代码块法
//            synchronized (this){
//                if(this.ticket>0){
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(Thread.currentThread().getName()+"还有"+
//                            (this.ticket--)+"张票");
//                }
//            }
            saleTicket();
        }
    }

    private synchronized void saleTicket(){
        if(this.ticket>0){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"还有"
                            +(this.ticket--)+"张票");
        }
    }
}

public class SynchronizedTest1 {

    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();

        Thread thread1 = new Thread(myThread1);
        Thread thread2 = new Thread(myThread1);
        Thread thread3 = new Thread(myThread1);

        thread1.start();
        thread2.start();
        thread3.start();
    }

}
