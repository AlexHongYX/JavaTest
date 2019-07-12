package com.xiaoaxiao.test.thread_test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by xiaoaxiao on 2019/7/12
 * Description: 实现Callable接口实现多线程，当线程有返回值时
 */

class MyCallable implements Callable<String>{

    private int ticket = 10;

    @Override
    public String call() throws Exception {
        while(this.ticket>0){
            System.out.println(Thread.currentThread().getName()+"，剩余票数："+this.ticket--);
        }
        return "票卖完了";
    }
}

public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 先取得Callable对象
        MyCallable myCallable = new MyCallable();
        // 将Callable对象转换为FutureTask对象
        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        // 由于FutureTask对象实现了RunnableFuture接口，
        // 而RunnableFuture接口又是Runnable接口的子接口，
        // 因此可以将FutureTask对象当做Runnable接口的实现类传给Thread
        Thread thread1 = new Thread(futureTask);
        Thread thread2 = new Thread(futureTask);
        // 一切的一切，最终的最终，开启线程还得是Thread的start方法
        thread1.start();
        thread2.start();
        System.out.println(futureTask.get());
    }

}
