package com.xiaoaxiao.test.thread_test.thread_basic_test;

/**
 * Created by xiaoaxiao on 2019/9/27
 * Description:
 */

class Join extends Thread{
    @Override
    public void run() {
        try {
            System.out.println("begin Timer="+System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("end Timer="+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class JoinTest {

    public static void main(String[] args) {
        Thread thread = new Join();
        thread.start();
        try {
            thread.join(2000);
            System.out.println("main end Timer:"+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
