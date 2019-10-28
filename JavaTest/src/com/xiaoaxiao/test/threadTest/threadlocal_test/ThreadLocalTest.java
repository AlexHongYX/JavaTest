package com.xiaoaxiao.test.thread_test.threadlocal_test;

/**
 * Created by xiaoaxiao on 2019/10/23
 * Description:
 */
public class ThreadLocalTest {

    private static ThreadLocal<String> threadLocal
            = new ThreadLocal<>();
    private static ThreadLocal<String> testLocal
            = new ThreadLocal<>();

    private static String strValue;

    public static void main(String[] args) throws InterruptedException {
        //  main线程
        strValue = "main线程修改";
        threadLocal.set("main线程修改");
//        testLocal.set("testLocal-main");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("Thread-A修改后的threadLocal");
                strValue = "Thread-A修改后的strValue";
                testLocal.set("testLocal-ThreadA");
            }
        }, "Thread-A");

        thread.start();

        thread.join();

        System.out.println(threadLocal.get());
        System.out.println(strValue);
        System.out.println(testLocal.get());
    }
}
