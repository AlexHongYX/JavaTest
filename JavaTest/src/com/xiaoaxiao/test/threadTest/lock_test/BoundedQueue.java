package com.xiaoaxiao.test.thread_test.lock_test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiaoaxiao on 2019/10/13
 * Description:
 *  有界队列：当队列为空时，队列的获取操作会阻塞获取线程，直到队列中有新增元素；
 *      当队列已满时，队列的插入操作会阻塞插入线程，直到队列出现“空位”
 *
 *  十分像“生产消费者”模型
 */
public class BoundedQueue<T> {
    private Object[] items;
    // 添加的下标，删除的下标，数组当前数量
    private int addIndex,removeIndex,count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size) {
        this.items = new Object[size];
    }

    // 添加一个元素，如果数组满，则将添加线程加入notFull等待队列
    // 添加线程进入等待状态，直到有“空位”
    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            // 必须要使用while，防止线程醒来后count再次被别的线程修改
            while (count==items.length){
                notFull.await();
            }
            items[addIndex] = t;
            if (++addIndex==items.length){
                addIndex = 0;
            }
            ++count;
            notEmpty.signal();
        }finally {

        }
    }

    // 由头部删除一个元素，如果数组空，则删除线程进入等待状态，直到有新元素添加
    @SuppressWarnings("unchecked")
    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (count==0){
                notEmpty.await();
            }
            Object x = items[removeIndex];
            if (++removeIndex == items.length){
                removeIndex = 0;
            }
            --count;
            notFull.signal();
            return (T)x;
        }finally {
            lock.unlock();
        }
    }
}
