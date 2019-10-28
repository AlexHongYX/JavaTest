package com.xiaoaxiao.test.design_pattern.producer_customer.pc_test2;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xiaoaxiao on 2019/10/24
 * Description: 生产消费者模型——模拟阻塞队列
 */

class Goods {
    private final String id;
    private String goodName;


    public Goods(String id, String goodName) {
        this.id = id;
        this.goodName = goodName;
    }

    public String getGoodName() {
        return goodName;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return goodName + id;
    }
}

class Producer implements Runnable {

    private final Queue<Goods> goods;

    private final Integer maxCapacity = 10;

    private final AtomicInteger id = new AtomicInteger(0);

    public Producer(Queue<Goods> goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this.goods) {
                if (this.goods.size() == maxCapacity) {
                    System.out.println("容器已满，请消费者消费");
                    try {
                        this.goods.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Goods newGood = new Goods(String.valueOf(id.getAndIncrement()), "商品");
                    this.goods.add(newGood);
                    System.out.println(Thread.currentThread().getName() + "生产：" + newGood);
                    this.goods.notifyAll();
                }
            }
        }
    }
}

class Customer implements Runnable {

    private final Queue<Goods> goods;

    public Customer(Queue<Goods> goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this.goods) {
                if (this.goods.isEmpty()) {
                    System.out.println("容器已空 等待生产者生产");
                    try {
                        this.goods.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Goods good = this.goods.poll();
                    if (good != null) {
                        System.out.println(Thread.currentThread().getName() + "消费:" + good);
                    }
                    this.goods.notifyAll();
                }
            }
        }
    }
}

public class ProductionCustomerPractice2 {

    public static void main(String[] args) {
        List<Thread> threadList = new ArrayList<>();
        Queue<Goods> goods = new LinkedList<>();


        // 这里必须设置共享的producer和customer
        // producer和customer都是Runnable对象
        // 而AtomicInteger是Runnable对象的一个属性，只有所有线程都共用一个Runnable对象时
        // 才能共用其AtomicInteger属性。

        // 如果每创建一个线程就新建一个Runnable对象，那么不同的线程获取的是不同对象的AtomicInteger属性
        // 最终的结果当然不能共享该属性。

        // 如果没有该属性的存在，那么共用一个Runnable或每次都新建一个Runnable就没什么区别了
        final Producer producer = new Producer(goods);
        final Customer customer = new Customer(goods);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(producer,"生产者"+i);
//            Thread thread = new Thread(new Producer(goods));
            threadList.add(thread);
        }

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(customer,"消费者"+i);
            threadList.add(thread);
        }

        for (Thread thread : threadList) {
            thread.start();
        }
    }
}
