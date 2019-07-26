package com.xiaoaxiao.queue_test.main;

import com.xiaoaxiao.queue_test.dao.MyQueueImpl;

/**
 * Created by xiaoaxiao on 2019/7/26
 * Description: 队列测试类
 */
public class TestMain {

    public static void main(String[] args) {
        MyQueueImpl myQueue = new MyQueueImpl();

        System.out.println(myQueue.empty());

        myQueue.add(11);
        myQueue.add(22);
        System.out.println(myQueue.size());
        myQueue.poll();
        myQueue.add(33);
        myQueue.add(44);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.size());

    }
}
