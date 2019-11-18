package test;

/**
 * Created by xiaoaxiao on 2019/11/18
 * Description:
 */
class OrderThread {

    private boolean flag=false;

    //子线程
    public synchronized void sub(){
        while(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        for(int i=0;i<10;i++){
            System.out.println("子线程"+i);
        }
        flag = true;
        this.notify();
    }

    //主线程
    public synchronized void main(){
        while(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for(int i=0;i<20;i++){
            System.out.println("主线程"+i);
        }
        flag = false;
        this.notify();
    }
}

class CallThread implements Runnable {
    private OrderThread orderThread;
    public CallThread(OrderThread orderThread){
        this.orderThread=orderThread;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for(int i=0;i<50;i++){
            orderThread.sub();
        }
    }

}

class CallThread2 implements Runnable {
    private OrderThread orderThread;
    public CallThread2(OrderThread orderThread){
        this.orderThread=orderThread;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for(int i=0;i<50;i++){
            orderThread.main();
        }
    }

}


public class Test {
    public static void main(String[] args) {
        OrderThread orderThread = new OrderThread();
        CallThread callThread = new CallThread(orderThread);
        CallThread2 callThread2 = new CallThread2(orderThread);
        Thread t = new Thread(callThread);
        Thread t2 = new Thread(callThread2);
        t.start();
        t2.start();
    }
}