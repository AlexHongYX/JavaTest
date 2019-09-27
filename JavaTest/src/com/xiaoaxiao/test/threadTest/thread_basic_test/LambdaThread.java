package com.xiaoaxiao.test.thread_test.thread_basic_test;

/**
 * Created by xiaoaxiao on 2019/9/27
 * Description: 使用匿名内部类/lambda表达式创建Runnable对象并传入到Thread的构造方法中
 */
public class LambdaThread {

    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        }).start();
        new Thread(()-> System.out.println(Thread.currentThread().getName())).start();

    }
}
