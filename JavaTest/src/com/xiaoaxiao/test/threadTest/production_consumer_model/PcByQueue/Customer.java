package com.xiaoaxiao.test.thread_test.production_consumer_model.PcByQueue;

import java.util.Queue;

/**
 * Created by xiaoaxiao on 2019/7/20
 * Description: 消费者类
 *          1、消费商品
 *          2、从容器中取出商品
 *          3、如果容器为空，消费等待，通知生产者生产
 */
public class Customer implements Runnable{

    private final Queue<Goods> goods;

    public Customer(Queue<Goods> goods) {
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
            synchronized (this.goods){

                if(this.goods.isEmpty()){
                    System.out.println(Thread.currentThread().getName()
                            +" 容器已空，通知生产者生产");
                    try {
                        this.goods.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 只有当容量为0时再释放等待队列中的线程（生产者）
                    this.goods.notifyAll();

                }else {
                    Goods newGood = this.goods.poll();
                    if(newGood!=null){
                        System.out.println(Thread.currentThread().getName()
                                        +" 消费商品 "+newGood);
                    }
                }

            }
        }
    }
}
