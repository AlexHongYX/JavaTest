package com.xiaoaxiao.test.jvm_test.volatile_test;

/**
 * Created by xiaoaxiao on 2019/8/3
 * Description: volatile的自增操作，由于没有保证原子性和有序性，仅保存可见性不能保证并发正确执行
 */
public class VolatileTest1 {

    public static volatile int num = 0;

    public static void increase() {
        num++;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        increase();
                    }
                }
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        while (Thread.activeCount() > 1){
            Thread.yield();
        }
        System.out.println(num);
    }
}
