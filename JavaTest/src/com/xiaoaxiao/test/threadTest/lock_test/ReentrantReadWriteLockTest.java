package com.xiaoaxiao.test.thread_test.lock_test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by xiaoaxiao on 2019/10/13
 * Description:
 */
public class ReentrantReadWriteLockTest {

    // HashMap是线程不安全的，使用读写锁，使得线程安全
    static Map<String,Object> map = new HashMap<>();
    static ReentrantReadWriteLock reentrantReadWriteLock
            = new ReentrantReadWriteLock();
    static Lock readLock = reentrantReadWriteLock.readLock();
    static Lock writeLock = reentrantReadWriteLock.writeLock();

    // 获取一个key对应的value——读锁
    public static final Object get(String key){
        readLock.lock();
        try {
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    // 设置key所对应的value，并返回旧的value
    public static final Object put(String key,Object value){
        writeLock.lock();
        try {
            return map.put(key, value);
        }finally {
            writeLock.unlock();
        }
    }
}
