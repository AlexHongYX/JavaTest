package com.xiaoaxiao.test.thread_test.lock_test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by xiaoaxiao on 2019/7/15
 * Description: 自定义锁（实现lock接口，并自定义静态内部类继承AQS）
 */

class MyLock implements Lock{

    MySync mySync = new MySync();

    static class MySync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {

            if(arg!=1){
                throw new RuntimeException("信号量不为1！");
            }

            if(compareAndSetState(0,1)){
                // 当前线程成功获取锁
                // 设置当前拥有独占访问权的线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if(getState() == 0){
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }

    @Override
    public void lock() {
        mySync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        mySync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return mySync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return mySync.tryAcquireNanos(1,time);
    }

    @Override
    public void unlock() {
        mySync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}

public class LockTest2 {

    public static void main(String[] args) {
        Lock myLock = new MyLock();
        for (int i=0;i<10;i++){
            new Thread(()->{
                try {
                    myLock.lock();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }finally {
                    myLock.unlock();
                }
            }).start();

        }
    }
}
