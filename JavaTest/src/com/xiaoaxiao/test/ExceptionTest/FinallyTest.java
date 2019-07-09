package com.xiaoaxiao.test.ExceptionTest;

/**
 * Created by xiaoaxiao on 2019/7/9
 * Description:
 */
public class FinallyTest {

    public static void main(String[] args) {
        System.out.println(test());
    }

    // 无论会不会抛出异常，都会返回2
    public static int test(){
        try {
            System.out.println(10/0);
            return 0;
        } catch (Exception e) {
            return 1;
        } finally {
            return 2;
        }
    }
}
