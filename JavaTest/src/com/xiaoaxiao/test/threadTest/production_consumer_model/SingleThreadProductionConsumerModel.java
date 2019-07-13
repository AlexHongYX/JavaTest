package com.xiaoaxiao.test.thread_test.production_consumer_model;

/**
 * Created by xiaoaxiao on 2019/7/13
 * Description: 单线程的生产消费者模式
 */

/**
 * 商品类
 */
class Goods{
    private String goodsName;
    private int count;

    // 生产商品方法
    public synchronized void set(String goodsName){
        if(this.count>5){
            try {
                // 等待消费者消费
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.goodsName = goodsName;
        this.count++;
        System.out.println(Thread.currentThread().getName()
                +"生产"+goodsName+toString());
        // 唤醒等待消费的线程
        notify();
    }

    // 消费商品方法
    public synchronized void get(){
        if(this.count == 0){
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
        notify();
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsName='" + goodsName + '\'' +
                ", count=" + count +
                '}';
    }
}

// 生产者类
class Producer implements Runnable{

    private Goods goods;

    public Producer(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        this.goods.set("西瓜");
    }
}

// 消费者类
class Customer implements Runnable{

    private Goods goods;

    public Customer(Goods goods) {
        this.goods = goods;

    }

    @Override
    public void run() {
        this.goods.get();
    }
}

public class SingleThreadProductionConsumerModel {

    public static void main(String[] args) {
        Goods goods = new Goods();
        Producer producer = new Producer(goods);
        Customer customer = new Customer(goods);

        Thread producerThread = new Thread(producer);
        Thread customerThread = new Thread(customer);

        customerThread.start();
        producerThread.start();

    }
}
