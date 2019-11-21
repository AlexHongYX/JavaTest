package com.xiaoaxiao.test.design_pattern.PC_practice.self_practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/11/21
 * Description:
 */

class Goods{
    private String name;
    private int count;
    private static final int MAX_COUNT = 10;

    public synchronized void get() throws InterruptedException {
        while (this.count==0){
            wait();
        }
        Thread.sleep(50);
        this.count--;
        System.out.println(Thread.currentThread().getName()+"消费了"+toString());
        notifyAll();
    }

    public synchronized void set(String goodName) throws InterruptedException {
        while (this.count==MAX_COUNT){
            wait();
        }
        Thread.sleep(50);
        this.name = goodName;
        this.count++;
        System.out.println(Thread.currentThread().getName()+"生产了"+toString());
        notifyAll();
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
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
                this.goods.set("BMW");
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

public class Practice {
    public static void main(String[] args) {
        Goods goods = new Goods();

        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(new Thread(new Producer(goods)));
        }

        for (int i = 0; i < 5; i++) {
            list.add(new Thread(new Customer(goods)));
        }

        for (Thread thread : list) {
            thread.start();
        }
    }
}
