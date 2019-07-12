package com.xiaoaxiao.test.thread_test;

/**
 * Created by xiaoaxiao on 2019/7/12
 * Description: 守护线程测试
 */

class DaemonThread implements Runnable{

    @Override
    public void run() {
        while (true){
            try {
                System.out.println("线程名称为："+Thread.currentThread().getName()
                        +"，是否为守护线程："+Thread.currentThread().isDaemon());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("线程"+Thread.currentThread().getName()+"中断了");
                return;
            }
        }
    }
}

public class DaemonThreadTest {

    public static void main(String[] args) throws InterruptedException {
        DaemonThread daemonThread = new DaemonThread();
        Thread threadA = new Thread(daemonThread,"A");
        Thread threadB = new Thread(daemonThread,"B");

        // 将A设置为守护线程，必须在A启动前
        threadA.setDaemon(true);
        threadA.start();

        threadB.start();

        Thread.sleep(3000);
        // 3秒后中断用户线程B
        threadB.interrupt();
        Thread.sleep(5000);
        System.out.println("程序结束");
    }
}
