package com.xiaoaxiao.test.thread_test;

/**
 * Created by xiaoaxiao on 2019/7/12
 * Description: 线程优先级
 */

class ThreadPriority implements Runnable{


    @Override
    public void run() {
        for (int i =0;i<5;i++){
            System.out.println("当前线程为："+Thread.currentThread().getName()+":"
                    +"优先级为："+Thread.currentThread().getPriority());
        }

//        new Thread(new ThreadPriority2(),"孙子线程").start();
    }
}

/**
 * 线程具有继承性（只是继承优先级而已）：从A线程启动B线程，则B和A的线程优先级是一样的。
 */
class ThreadPriority2 implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+":"
                +Thread.currentThread().getPriority());
    }
}



public class ThreadPriorityTest {

    public static void main(String[] args) {
//        ThreadPriority threadPriority = new ThreadPriority();
//        Thread thread = new Thread(threadPriority,"子线程");
//        System.out.println(Thread.currentThread().getName()+":"
//                +Thread.currentThread().getPriority());
//        thread.start();
        ThreadPriority threadPriority = new ThreadPriority();
//        Thread thread = new Thread(threadPriority,"子线程");
//        System.out.println(Thread.currentThread().getName()+":"
//                +Thread.currentThread().getPriority());
//        thread.start();
        Thread thread1 = new Thread(threadPriority,"A");
        Thread thread2 = new Thread(threadPriority,"B");
        Thread thread3 = new Thread(threadPriority,"C");

        // 即便是设置了优先级，可不一定是优先级越大越先执行
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.NORM_PRIORITY);
        thread3.setPriority(Thread.MAX_PRIORITY);

        thread1.start();;
        thread2.start();;
        thread3.start();
        System.out.println("程序结束");
    }
}
