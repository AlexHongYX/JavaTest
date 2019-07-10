package com.xiaoaxiao.test.LambdaTest;

/**
 * Created by xiaoaxiao on 2019/7/10
 * Description: 当Lambda表达式只有一行代码并且存在返回值时
 */

interface IInterface2{
    int test(int a,int b);
}

public class OneLineCodeReturn {
    public static void main(String[] args) {
        IInterface2 iInterface2 = (p1,p2)->p1+p2;
        System.out.println(iInterface2.test(10,20));
    }
}
