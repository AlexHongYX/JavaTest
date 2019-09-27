package com.xiaoaxiao.test.thread_test.thread_basic_test;

/**
 * Created by xiaoaxiao on 2019/7/12
 * Description: 测试thread常用的方法——线程停止-设置标记符
 */

class MyRunnable4 implements Runnable{

    // 设置一个标记符
    private Boolean flag = true;

    @Override
    public void run() {
        int i=1;
        // 将该标记符当做线程持续进行的条件
        while (flag){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("这是第"+i+"次执行"+"线程名称为："+Thread.currentThread().getName());
            i++;
        }
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}

public class threadMethodTest3 {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable4 myRunnable4 = new MyRunnable4();
        Thread thread = new Thread(myRunnable4);
        thread.start();
        Thread.sleep(5000);
        // 主线程睡眠5s后将flag设置为false，
        // 当子线程再次访问是，flag已经变为false
        myRunnable4.setFlag(false);
        // 调用子线程的join，让主线程等待子线程执行完成后再执行
        thread.join();
        System.out.println("代码结束");
    }
}



