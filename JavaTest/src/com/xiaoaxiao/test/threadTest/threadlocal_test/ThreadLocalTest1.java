package com.xiaoaxiao.test.thread_test.threadlocal_test;

/**
 * Created by xiaoaxiao on 2019/7/20
 * Description: ThreadLocal类测试
 */
public class ThreadLocalTest1 {

    private static String commStr;
    private static ThreadLocal<String> threadStr = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        commStr = "main";
        threadStr.set("main");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                commStr = "thread";
                threadStr.set("thread");
            }
        });
        thread.start();

        // 等待子线程执行完再执行主线程
        thread.join();

        System.out.println(commStr);
        System.out.println(threadStr.get());
    }

}
