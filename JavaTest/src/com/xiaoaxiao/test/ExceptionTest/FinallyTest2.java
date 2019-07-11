package com.xiaoaxiao.test.exceptionTest;

/**
 * Created by xiaoaxiao on 2019/7/9
 * Description:
 */
public class FinallyTest2 {

    public static void main(String[] args) {
        try{
            System.out.println("1");
            System.exit(0);
        }catch (Exception e){
            System.out.println("2");
        }finally {
            System.out.println("3");
        }
    }
}
