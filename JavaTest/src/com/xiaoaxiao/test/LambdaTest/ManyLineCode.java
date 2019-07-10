package com.xiaoaxiao.test.LambdaTest;

/**
 * Created by xiaoaxiao on 2019/7/10
 * Description: 当Lambda表达式有多行代码时
 */

interface IInterface3{
    int test(int a,int b);
}

public class ManyLineCode {

    public static void main(String[] args) {
        IInterface3 interface3 = (p1,p2)->{
            int ret = p1+p2;
            ret += 10;
            return ret;
        };

        System.out.println(interface3.test(10,20));
    }
}
