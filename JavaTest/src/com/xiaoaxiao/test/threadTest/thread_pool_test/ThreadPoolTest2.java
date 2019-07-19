package com.xiaoaxiao.test.thread_test.thread_pool_test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by xiaoaxiao on 2019/7/19
 * Description: 线程池创建测试 submit
 */
public class ThreadPoolTest2 {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                5,
                8,
                10,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(10)
        );

        final List<Future<String>> futureList = new ArrayList<>();

        for (int i=0;i<10;i++){
            Future<String> future = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return (Thread.currentThread().getName()+" "+ LocalDateTime.now());
                }
            });
            futureList.add(future);
        }

        for (Future<String> future : futureList){
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdownNow();

        System.out.println("isShutdown?"+executorService.isShutdown());
        System.out.println("isTerminated?"+executorService.isTerminated());
    }
}
