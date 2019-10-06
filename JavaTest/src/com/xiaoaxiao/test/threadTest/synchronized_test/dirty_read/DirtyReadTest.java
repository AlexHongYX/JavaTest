package com.xiaoaxiao.test.thread_test.synchronized_test.dirty_read;

/**
 * Created by xiaoaxiao on 2019/10/6
 * Description: 多个线程访问实例变量会产生"脏读"
 *              需要加synchronized锁避免"脏读"
 */

class MyThread implements Runnable{

    private int num = 5;

    @Override
    public void run() {
        showNum();
    }

    public synchronized void showNum(){
        while (num>0){
            num--;
            System.out.println(Thread.currentThread().getName()+":"+num);
        }
    }
}

public class DirtyReadTest {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        new Thread(myThread,"A").start();
        new Thread(myThread,"B").start();
    }
}
