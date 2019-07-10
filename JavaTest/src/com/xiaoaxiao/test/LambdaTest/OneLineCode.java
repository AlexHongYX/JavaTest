package com.xiaoaxiao.test.LambdaTest;

/**
 * Created by xiaoaxiao on 2019/7/10
 * Description: 当Lambda表达式只有一行代码且没有返回值时
 */

interface IInterface1{
    void test();
}

public class OneLineCode {

    public static void main(String[] args) {
        IInterface1 interface1 = ()-> System.out.println("hello world");
        interface1.test();
    }
}
