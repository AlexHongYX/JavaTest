package com.xiaoaxiao.test.other_test;

/**
 * Created by xiaoaxiao on 2019/9/27
 * Description: 方法覆写
 */

class Father{
    public Object hehe(int a,int b){
        System.out.println(a+b);
        return a+b;
    }
}

class Child extends Father{
    public String hehe(int a,int b){
        System.out.println(a+b);
        return String.valueOf(a+b);
    }
}

public class MethodTest {
}
