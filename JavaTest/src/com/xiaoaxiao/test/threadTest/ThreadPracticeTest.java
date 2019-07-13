package com.xiaoaxiao.test.thread_test;

/**
 * Created by xiaoaxiao on 2019/7/12
 * Description: 用多线程+同步操作实现
             * 	*Thread-0
             * 	*Thread-0@Thread-1
             * 	*Thread-0@Thread-1#Thread-2
             * 	*Thread-0@Thread-1#Thread-2*Thread-0
             * 	*Thread-0@Thread-1#Thread-2*Thread-0@Thread-1
             *  *Thread-0@Thread-1#Thread-2*Thread-0@Thread-1#Thread-2
 */

class ThreadPractice implements Runnable{

    private String ret = "";

    private Integer count = 0;

    @Override
    public void run() {

        while (true){
            if(count%3!=Integer.parseInt((Thread.currentThread().getName().substring(7,8)))){
                    Thread.yield();
            }
//            System.out.println((Thread.currentThread().getName().substring(7,8)));
            print();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }

    }

    private synchronized void print(){
//        System.out.println(Thread.currentThread().getName());
        String flag;

        if(Thread.currentThread().getName().equals("Thread-0")){
            flag = "*Thread-0";
        }else if(Thread.currentThread().getName().equals("Thread-1")){
            flag = "@Thread-1";
        }else{
            flag = "#Thread-2";
        }

        this.ret = this.ret + flag;
        System.out.println(this.ret);
    }
}

public class ThreadPracticeTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPractice threadPractice = new ThreadPractice();
        Thread thread0 = new Thread(threadPractice);
        Thread thread1 = new Thread(threadPractice);
        Thread thread2 = new Thread(threadPractice);

            thread0.start();


            thread1.start();


            thread2.start();



    }

}
