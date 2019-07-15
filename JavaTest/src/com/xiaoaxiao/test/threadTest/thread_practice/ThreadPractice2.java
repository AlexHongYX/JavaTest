package com.xiaoaxiao.test.thread_test.thread_practice;

/**
 * Created by xiaoaxiao on 2019/7/15
 * Description: 编写一个程序，启动三个线程，三个线程的名称分别是A,B,C
 *              每个线程将自己的名称在屏幕上打印5遍，打印顺序是ABCABC
 */

//class MyThread2 implements Runnable{
//
//    private int flag = 1;
//
//    @Override
//    public void run() {
//
//    }
//
//    public synchronized void printA(){
//        while (flag != 1){
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        System.out.print("A");
//
//        flag = 2;
//        notifyAll();
//    }
//
//    public synchronized void printB(){
//        while (flag != 2){
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        System.out.print("B");
//
//        flag = 3;
//        notifyAll();
//    }
//
//    public synchronized void printC(){
//        while (flag != 3){
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        System.out.print("C");
//
//        flag = 1;
//        notifyAll();
//    }
//}

class Print{

    private int flag = 1;
    private int count = 0;

    public int getCount() {
        return count;
    }

    public synchronized void printA(){
        while (flag != 1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.print(Thread.currentThread().getName());

        count++;
        flag = 2;
        notifyAll();
    }

    public synchronized void printB(){
        while (flag != 2){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.print(Thread.currentThread().getName());

        count++;
        flag = 3;
        notifyAll();
    }

    public synchronized void printC(){
        while (flag != 3){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.print(Thread.currentThread().getName());

        count++;
        flag = 1;
        notifyAll();
    }
}

class MyThread3 implements Runnable{

    Print print = new Print();

    @Override
    public void run() {
        while (this.print.getCount()<16){
            if(Thread.currentThread().getName().equals("A")){
                print.printA();
            }else if(Thread.currentThread().getName().equals("B")){
                print.printB();
            }else {
                print.printC();
            }
        }
    }
}

public class ThreadPractice2 {

    public static void main(String[] args) {

        /**
         * 在主函数中每个线程重复调用
         */
//        MyThread2 mt = new MyThread2();
//
//        new Thread(()->{
//            for(int i=0;i<5;i++){
//                mt.printA();
//            }
//        }).start();
//
//        new Thread(()->{
//            for(int i=0;i<5;i++){
//                mt.printB();
//            }
//        }).start();
//
//        new Thread(()->{
//            for(int i=0;i<5;i++){
//                mt.printC();
//            }
//        }).start();

        MyThread3 mt = new MyThread3();

        Thread threadA = new Thread(mt,"A");
        Thread threadB = new Thread(mt,"B");
        Thread threadC = new Thread(mt,"C");

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
