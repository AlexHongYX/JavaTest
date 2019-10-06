package com.xiaoaxiao.test.thread_test.synchronized_test.dirty_read;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/10/6
 * Description: 使用了synchronized锁但不一定会避免"脏读"
 *              由于两个线程以异步的方式返回list参数的size()大小，产生了"脏读"
 *
 *              这段代码明明使用了synchronized同步代码块了，但却没有输出正确的结果，
 *              其原因是两个线程调用了非同步的方法addServiceMethod()，
 *              虽然add()是同步的，但两个线程都可以异步进入addServiceMethod()，
 *              并在进行if判断时，两个线程都可以进入if语句块中(异步，
 *              因此两个线程进入是size都==0)，只不过是两个线程顺序的执行了add()方法
 *              (同步方法)，因此最终的结果为2。
 */

class MyOneList{
    private List list = new ArrayList();
    public synchronized void add(String data){
        list.add(data);
    }
    public synchronized int getSize(){
        return list.size();
    }
}

/**
 * 创建一个只含1个元素的list
 */
class MyService{
    public MyOneList addServiceMethod(MyOneList list,String data){
        try {
            synchronized (list){
                if (list.getSize()<1){
                    // 模拟从远程花费2s取回数据
                    Thread.sleep(2000);
                    list.add(data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

}

// 两个线程分别插入"A"和"B"
class MyThread1 extends Thread{
    private MyOneList list;

    public MyThread1(MyOneList list) {
        this.list = list;
    }

    @Override
    public void run() {
        MyService myService = new MyService();
        myService.addServiceMethod(list,"A");
    }
}

class MyThread2 extends Thread{
    private MyOneList list;

    public MyThread2(MyOneList list) {
        this.list = list;
    }

    @Override
    public void run() {
        MyService myService = new MyService();
        myService.addServiceMethod(list,"B");
    }
}

public class DirtyReadTest3 {

    public static void main(String[] args) throws InterruptedException {
        MyOneList list = new MyOneList();
        MyThread1 thread1 = new MyThread1(list);
        thread1.setName("A");
        MyThread2 thread2 = new MyThread2(list);
        thread2.setName("B");

        thread1.start();
        thread2.start();

        Thread.sleep(6000);
        System.out.println("listSize = "+list.getSize());
    }
}
