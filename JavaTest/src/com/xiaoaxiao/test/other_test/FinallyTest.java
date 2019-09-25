package com.xiaoaxiao.test.other_test;

/**
 * Created by xiaoaxiao on 2019/9/25
 * Description: 测试finally的返回情况
 */
public class FinallyTest {

    public static void main(String[] args) {
        System.out.println(test());
    }

    public static int test(){
        try {
            System.out.println(10/0);
            return 0;
        }catch (Exception e){
            return 1;
        }finally {
            System.out.println("finally");
            return 2;
        }
    }
}
