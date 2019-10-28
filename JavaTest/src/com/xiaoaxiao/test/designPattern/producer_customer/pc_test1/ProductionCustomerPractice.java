package com.xiaoaxiao.test.design_pattern.producer_customer.pc_test1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/10/22
 * Description: 生产者消费者模型——wait()+notify()
 */

class Goods{
    private String goodName;
    private int count;
    private int maxCount = 10;

    public synchronized void set(String goodName) throws InterruptedException {
        while (this.count == maxCount){
            wait();
        }
        Thread.sleep(200);
        this.goodName = goodName;
        this.count++;
        System.out.println(Thread.currentThread().getName()+"生产："+toString());
        notifyAll();
    }

    public synchronized void get() throws InterruptedException {
        while (this.count==0){
            wait();
        }
        Thread.sleep(200);
        this.count = this.count-1;
        System.out.println(Thread.currentThread().getName()+"消费："+toString());
        notifyAll();
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodName='" + goodName + '\'' +
                ", count=" + count +
                '}';
    }
}

class Producer implements Runnable{

    private Goods goods;

    public Producer(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while (true){
            try {
                this.goods.set("奔驰");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Customer implements Runnable{
    private Goods goods;

    public Customer(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while (true){
            try {
                this.goods.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ProductionCustomerPractice {
    public static void main(String[] args) {
        Goods goods = new Goods();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Producer(goods));
            threadList.add(thread);
        }
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Customer(goods));
            threadList.add(thread);
        }

        for(Thread thread:threadList){
            thread.start();
        }
        
        
    }
}
