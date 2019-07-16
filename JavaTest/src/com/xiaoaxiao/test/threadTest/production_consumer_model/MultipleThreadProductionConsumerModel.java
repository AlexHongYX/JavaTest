package com.xiaoaxiao.test.thread_test.production_consumer_model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/7/15
 * Description: 多线程的生产消费模式
 */

class Goods1{
    private String goodsName;
    private int count;
    private int maxCount;

    public Goods1(int maxCount) {
        this.maxCount = maxCount;
    }

    // 生产商品方法
    public synchronized void set(String goodsName){
        while (this.count==maxCount){
            try {
                // 等待消费者消费
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.goodsName = goodsName;
        this.count++;
        System.out.println(Thread.currentThread().getName()
                +"生产"+goodsName+toString());
        // 唤醒等待的线程
        notifyAll();
    }

    // 消费商品方法
    public synchronized void get(){
        // 这里必须要使用while语句，保证每一次进入某一线程都要判断一下当前this.count是否为0
        // 错误实例：消费线程1与消费线程2都处于等待状态，当这两个线程被一个生产线程唤醒时，消费线程2先进入将count--，
        // 如果不用while语句，消费线程1后进入时也会将count--，导致错误（数量产生负数）
        while (this.count == 0){
            System.out.println("卖完了");
            try {
                // 等待生产者生产
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.count--;
        System.out.println(Thread.currentThread().getName()
                +"消费"+goodsName+toString());
        // 唤醒等待中的生产者线程
        notifyAll();
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsName='" + goodsName + '\'' +
                ", count=" + count +
                '}';
    }
}

class Producer1 implements Runnable{

    private Goods1 goods;

    public Producer1(Goods1 goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while (true) {
            this.goods.set("西瓜");
        }
    }
}

class Customer1 implements Runnable{

    private Goods1 goods;

    public Customer1(Goods1 goods) {
        this.goods = goods;

    }

    @Override
    public void run() {
        // 不断消费
        while (true) {
            this.goods.get();
        }
    }
}

public class MultipleThreadProductionConsumerModel {

    public static void main(String[] args) {

        Goods1 goods1 = new Goods1(10);

        List<Thread> threadList = new ArrayList<>();

        for(int i=0;i<5;i++){
            Thread producerThread = new Thread(new Producer1(goods1),"生产者"+i);
            threadList.add(producerThread);
        }

        for (int i=0;i<10;i++){
            Thread customerThread = new Thread(new Customer1(goods1),"消费者"+i);
            threadList.add(customerThread);
        }

        for (Thread thread:threadList){
            thread.start();
        }

    }
}
