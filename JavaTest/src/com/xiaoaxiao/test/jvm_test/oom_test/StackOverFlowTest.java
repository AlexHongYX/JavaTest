package com.xiaoaxiao.test.jvm_test.oom_test;

/**
 * Created by xiaoaxiao on 2019/7/29
 * Description: Java栈溢出（单线程情况下抛出StackOverFlowError）
 *               多线程会抛出OutOfMemoryError，Java线程时映射到操作系统的内核线程上
 *               多线程的栈溢出可能会导致操作系统假死，不进行测试了
 */
public class StackOverFlowTest {

    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackOverFlowTest stackOverFlowTest = new StackOverFlowTest();
        try {
            stackOverFlowTest.stackLeak();
        } catch (Throwable e) {     //Error，用Throwable检测
            System.out.println("stack length:"+stackOverFlowTest.stackLength);
        }

    }
}
