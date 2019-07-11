package com.xiaoaxiao.test.exceptionTest;

/**
 * Created by xiaoaxiao on 2019/7/9
 * Description: ThrowTest
 */
public class ThrowTest1 {

    public static void main(String[] args){
        try {
            test();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static void test() throws RuntimeException{
        throw new RuntimeException("hehe");
    }
}
