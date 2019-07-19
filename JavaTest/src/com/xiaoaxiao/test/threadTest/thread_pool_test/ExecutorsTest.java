package com.xiaoaxiao.test.thread_test.thread_pool_test;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xiaoaxiao on 2019/7/19
 * Description: 测试FixedThreadPool，无界队列
 */
public class ExecutorsTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i=0;i<2;i++){
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
