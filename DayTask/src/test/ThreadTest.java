package test;

/**
 * Created by xiaoaxiao on 2019/11/18
 * Description:
 */

class RunnableA implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            System.out.println("线程A");
        }
    }
}

class RunnableB implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("线程B");
        }
    }
}


public class ThreadTest{
    public static void main(String[] args) {
        Runnable runnableA = new RunnableA();
        Runnable runnableB = new RunnableB();
        Runnable runnableChild = new ChildRunnable();

        try {
            for (int i = 0; i < 5; i++) {
                Thread threadA = new Thread(runnableA);
                threadA.start();
                threadA.join();

                Thread threadB = new Thread(runnableB);
                threadB.start();
                threadB.join();

                Thread childThread = new Thread(runnableChild);
                childThread.start();
                childThread.join();
                for (int j = 0; j < 3; j++) {
                    System.out.println("主线程");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

class ChildRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            System.out.println("子线程");
        }
    }
}
