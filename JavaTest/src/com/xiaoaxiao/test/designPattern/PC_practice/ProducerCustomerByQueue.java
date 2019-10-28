package com.xiaoaxiao.test.design_pattern.PC_practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xiaoaxiao on 2019/10/28
 * Description: 生产者消费者模型——自定义阻塞队列
 *      会写这一种就行了，生产消费耦合，和Condition机制的知道即可
 *          那两种，耦合度太高，while有点烦，不容易写
 *         这种能完全体现阻塞队列+生产者消费者的关系
 */

class Goods{
    private String name;

    public String getName() {
        return name;
    }

    public Goods(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Producer implements Runnable{

    private final Queue<Goods> goods;
    private int maxCapacity = 10;
    private AtomicInteger id = new AtomicInteger(0);

    public Producer(Queue<Goods> goods) {
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
            synchronized (this.goods){
                if(this.goods.size()==maxCapacity){
                    System.out.println("队列已满，等待消费");
                    try {
                        this.goods.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    Goods good = new Goods("商品"+id.incrementAndGet());
                    this.goods.add(good);
                    System.out.println(Thread.currentThread().getName()+"生产了："+good);
                    this.goods.notifyAll();
                }
            }
        }
    }
}

class Customer implements Runnable{

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
            synchronized (this.goods){
                if(this.goods.isEmpty()){
                    System.out.println("容器已空，请生产者生产");
                    try {
                        this.goods.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    Goods good = this.goods.poll();
                    if (good!=null){
                        System.out.println(Thread.currentThread().getName()+"消费了："+good);
                    }
                    this.goods.notifyAll();
                }
            }
        }

    }
}

public class ProducerCustomerByQueue {

    public static void main(String[] args) {

        Queue<Goods> goods = new LinkedList<>();

        Producer producer = new Producer(goods);
        Customer customer = new Customer(goods);

        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(producer,"生产者"+i);
            list.add(thread);
        }

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(customer,"消费者"+i);
            list.add(thread);
        }

        for (Thread thread : list){
            thread.start();
        }

    }
}
