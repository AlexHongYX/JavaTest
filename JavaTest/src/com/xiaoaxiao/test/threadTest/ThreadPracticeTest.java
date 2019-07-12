package com.xiaoaxiao.test.thread_test;

/**
 * Created by xiaoaxiao on 2019/7/12
 * Description: 用多线程+同步操作实现
             * 	*Thread-0
             * 	*Thread-0@Thread-1
             * 	*Thread-0@Thread-1#Thread-2
             * 	*Thread-0@Thread-1#Thread-2*Thread-0
             * 	*Thread-0@Thread-1#Thread-2*Thread-0@Thread-1
             * *Thread-0@Thread-1#Thread-2*Thread-0@Thread-1#Thread-2
 */

class ThreadPractice implements Runnable{

    String ret = "";

    @Override
    public void run() {
        print();
    }

    private synchronized void print(){
//        System.out.println(Thread.currentThread().getName());
        String flag;

        int i = Integer.parseInt(Thread.currentThread().getName());
        if(i%3==0){
            flag = "*Thread-0";
        }else if(i%3==1){
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
//        Thread thread0 = new Thread(threadPractice);
//        Thread thread1 = new Thread(threadPractice);
//        Thread thread2 = new Thread(threadPractice);
//        while(true){
//            thread0.start();
//
//            Thread.sleep(1000);
//
//            thread1.start();
//
//            Thread.sleep(1000);
//
//            thread2.start();
//        }
        for(int i=0;i<1000;i++){
           new Thread(threadPractice,""+i).start();
           Thread.sleep(1000);
        }


    }

}
