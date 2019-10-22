package com.xiaoaxiao.test.inherit_test;

import com.xiaoaxiao.test.other_test.ClassTask2;

import java.util.Calendar;

/**
 * Created by xiaoaxiao on 2019/8/24
 * Description:
 */

class Test{
    static int n = 5;
    static {
        n = 10;
    }

    public Test(int x){

    }

    public Test(){

    }
}

class Child extends Test{

    public Child(int x){
        super();
    }

    public static void show(){
        System.out.println(n);
    }
}

public class StaticTest {

    public static void main(String[] args) {
        System.out.println(Test.n);

        Child.show();

    }
}
