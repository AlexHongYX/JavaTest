package com.xiaoaxiao.test.jvm_test;

/**
 * Created by xiaoaxiao on 2019/7/29
 * Description: finalize()对象的自我拯救方法
 */
public class FinalizeTest {

    public static FinalizeTest finalizeTest;

    public void isAlive() {
        System.out.println("I am alive:)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        finalizeTest = this;
    }

    public static void main(String[] args) {
        finalizeTest = new FinalizeTest();
        // 第一次自救
        finalizeTest = null;
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (finalizeTest != null){
            finalizeTest.isAlive();
        }else {
            System.out.println("no i am dead:(");
        }
        // 第二次自救
        finalizeTest = null;
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (finalizeTest != null){
            finalizeTest.isAlive();
        }else {
            System.out.println("no i am dead:(");
        }
    }
}
