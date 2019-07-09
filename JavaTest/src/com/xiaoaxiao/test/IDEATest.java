package com.xiaoaxiao.test;

/**
 * Created by xiaoaxiao on 2019/7/9
 * Description:
 */

interface IMessage1{
    void fun();
    void test();
    void fun1();
    void fun2();
}

class MessageImpl1 implements IMessage1{

    @Override
    public void fun() {

    }

    @Override
    public void test() {

    }

    @Override
    public void fun1() {

    }

    @Override
    public void fun2() {

    }
}

public class IDEATest {
    public static void main(String[] args) {
        for (int i=0;i<5;i++) {
            System.out.println("Hello world");
        }
    }
}
