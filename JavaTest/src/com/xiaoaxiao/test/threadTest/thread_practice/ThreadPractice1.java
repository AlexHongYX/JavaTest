package com.xiaoaxiao.test.thread_test.thread_practice;

/**
 * Created by xiaoaxiao on 2019/7/15
 * Description: 写两个线程，一个线程打印1~52，另一个线程打印A~Z，
 *              打印顺序为：12A34B...5152Z
 */

class MyThread implements Runnable{

    private int flag = 1;
    private int count = 1;

    @Override
    public void run() {
        if(flag == 1){
            printNum();
        }else {
            printLetter();
        }
    }

    public synchronized void printNum(){
        while (flag != 1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.print(2*count-1);
        System.out.print(2*count);

        flag = 2;
        notify();
    }

    public synchronized void printLetter(){
        while (flag != 2){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.print((char)((count-1)+'A'));
        count++;

        flag = 1;
        notify();
    }
}

public class ThreadPractice1 {

    public static void main(String[] args) {
        MyThread mt = new MyThread();

        new Thread(()->{
           for (int i=0;i<26;i++){
               mt.printNum();
           }
        }).start();

        new Thread(()->{
            for(int i=0;i<26;i++){
                mt.printLetter();
            }
        }).start();
    }
}
