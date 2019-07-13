package com.xiaoaxiao.test.thread_test.production_consumer_model;

/**
 * Created by xiaoaxiao on 2019/7/13
 * Description: wait(),notify(),notifyAll()测试
 */

class MyThread1 implements Runnable{

    private Object object;
    private boolean flag;

    public MyThread1(Object object, boolean flag) {
        this.object = object;
        this.flag = flag;
    }

    @Override
    public void run() {
        if(flag){
            waitTest();
        }else {
            notifyTest();
        }
    }

    private void waitTest(){
        synchronized (object){
            System.out.println("wait"+Thread.currentThread().getName()+"方法开始");
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("wait"+Thread.currentThread().getName()+"方法结束");
        }
    }

    private void notifyTest(){
        synchronized (object){
            System.out.println("notify方法开始");
//            object.notify();
            object.notifyAll();
            System.out.println("notify方法结束");
        }
    }
}

public class WaitAndNotifyTest {

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        MyThread1 waitThread = new MyThread1(object,true);
        for(int i=0;i<10;i++){
            Thread thread1 = new Thread(waitThread,"Thread"+i);
            thread1.start();
        }
        MyThread1 notifyThread = new MyThread1(object,false);
        Thread thread2 = new Thread(notifyThread);

        Thread.sleep(1000);
        thread2.start();

    }
}
