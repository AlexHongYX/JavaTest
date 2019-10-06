package com.xiaoaxiao.test.thread_test.synchronized_test.dirty_read;

/**
 * Created by xiaoaxiao on 2019/10/6
 * Description: getter未加锁导致的"脏读"
 */

class PublicVar{
    private String username = "A";
    private String password = "AA";

    public synchronized void setValue(String username,String password){
        try {
            this.username = username;
            Thread.sleep(5000);
            this.password = password;
            System.out.println("setValue method thread name="
                        +Thread.currentThread().getName()+" username="
                        +username+" password="+password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 会产生"脏读"
//    public void getValue(){
//        System.out.println("getValue method thread name="
//                +Thread.currentThread().getName()+" username="
//                +username+" password="+password);
//    }

    // 避免了"脏读"的产生
    public synchronized void getValue(){
        System.out.println("getValue method thread name="
                +Thread.currentThread().getName()+" username="
                +username+" password="+password);
    }
}

class ThreadA extends Thread{

    PublicVar publicVar = null;

    public ThreadA(PublicVar publicVar) {
        this.publicVar = publicVar;
    }

    @Override
    public void run() {
        publicVar.setValue("B","BB");
    }
}

public class DirtyReadTest2 {

    public static void main(String[] args) {

        try {
            PublicVar publicVar = new PublicVar();
            ThreadA threadA = new ThreadA(publicVar);
            threadA.start();

            // 程序的结果与这个睡眠时间相关
            // 此处的睡眠时间>setValue()中的睡眠时间：等设置完值后才取得值，不会出现脏读
            // 此处的睡眠时间<setValue()中的睡眠时间：没等设置完值就取得值，会出现脏读
            Thread.sleep(200);
            publicVar.getValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
