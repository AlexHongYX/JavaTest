package com.xiaoaxiao.test.thread_test.production_consumer_model.PcByQueue;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xiaoaxiao on 2019/7/20
 * Description: 生产者类
 *          1、生产商品
 *          2、将生产的商品添加到容器中
 *          3、如果容器满了，生产等待，通知消费者消费
 */
public class Producer implements Runnable{

    private final Queue<Goods> goods;

    private final Integer maxCapacity = 10;

    // 创建一个原子变量，多个线程共享且线程安全
    private final AtomicInteger id = new AtomicInteger(0);

    public Producer(Queue<Goods> goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 将这个容器锁住
            synchronized (this.goods){
                if(this.goods.size()==maxCapacity){
                    System.out.println(Thread.currentThread().getName()
                            +" 容器满了 等待消费");
                    try {
                        this.goods.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 只有当容量已满时再释放等待队列中的线程（消费者）
                    this.goods.notifyAll();
                }else {
                    Goods newGood = new Goods(
                            // 获取id并+1
                            String.valueOf(id.getAndIncrement()),
                            "商品"
                    );

                    this.goods.add(newGood);

                    System.out.println(Thread.currentThread().getName()
                                +" 生产商品 "+ newGood);
                }

            }
        }
    }

}
