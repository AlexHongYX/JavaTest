package com.xiaoaxiao.test.thread_test.thread_pool_test;

import java.time.LocalDateTime;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xiaoaxiao on 2019/7/19
 * Description: 线程池创建测试 execute
 */
public class ThreadPoolTest1 {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                5,
                8,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(10),
                // 自定义线程池中线程的属性
                new ThreadFactory() {

                    // 线程安全的Integer
                    private final AtomicInteger id = new AtomicInteger(0);

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("Thread-Execute-Task-"+id.getAndIncrement());
                        return thread;
                    }
                }
        );

        for (int i=0;i<10;i++){
            // 线程池启动线程
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()
                            +" "+ LocalDateTime.now());
                }
            });
        }
    }
}
