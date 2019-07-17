package com.xiaoaxiao.test.thread_test.book_test;

/**
 * Created by xiaoaxiao on 2019/7/17
 * Description: 死锁
 */

class DealThread implements Runnable{

    public String username;
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    public void setFlag(String username){
        this.username = username;
    }

    @Override
    public void run() {
        if (username.equals("a")){
            synchronized (lock1){
                try {
                    System.out.println("username = "+username);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println(" 按lock1->lock2 代码顺序执行");
                }
            }
        }
        if (username.equals("b")){
            synchronized (lock2){
                try {
                    System.out.println("username = "+username);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println(" 按lock2->lock1 代码顺序执行");
                }
            }
        }
    }


}

public class DealThreadTest {

    public static void main(String[] args) {

        try {
            DealThread t1 = new DealThread();
            t1.setFlag("a");
            Thread thread1 = new Thread(t1);
            thread1.start();
            Thread.sleep(1000);
            t1.setFlag("b");
            Thread thread2 = new Thread(t1);
            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
