package com.xiaoaxiao.test.thread_test.production_consumer_model.PcByQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * Created by xiaoaxiao on 2019/7/20
 * Description: 生产消费模型测试类
 */
public class PCTest {

    public static void main(String[] args) {

        final Queue<Goods> goods = new LinkedList<>();

        List<Thread> threadList = new ArrayList<>();

        // 创建一个Producer和Customer的对象和创建线程Thread时
        // 和每次都new一个新的Producer效果一样，但节省内存空间
        final Producer producer = new Producer(goods);
        final Customer customer = new Customer(goods);

        // 创建生产者线程
        for (int i=0;i<5;i++){
            Thread thread = new Thread(producer,"生产者"+i);
            threadList.add(thread);
        }

        // 创建消费者线程
        for (int i=0;i<10;i++){
            Thread thread = new Thread(customer,"消费者"+i);
            threadList.add(thread);
        }

        // 通过threadList统一启动所有线程
        for (Thread thread : threadList){
            thread.start();
        }
    }
}
