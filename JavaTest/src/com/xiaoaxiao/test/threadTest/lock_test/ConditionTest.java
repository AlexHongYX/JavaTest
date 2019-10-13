package com.xiaoaxiao.test.thread_test.lock_test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiaoaxiao on 2019/10/13
 * Description:
 */
public class ConditionTest {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void conditionWait() throws InterruptedException {
        lock.lock();
        try {
            condition.await();
        }finally {
            lock.unlock();
        }
    }

    public void conditionSignal(){
        lock.lock();
        try {
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}
