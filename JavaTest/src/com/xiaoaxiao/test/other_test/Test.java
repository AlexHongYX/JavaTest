package com.xiaoaxiao.test.other_test;

/**
 * Created by xiaoaxiao on 2019/8/25
 * Description:
 */
public class Test{
    {
        System.out.println("1.Test的构造块");
    }
    public Test(){
        System.out.println("2.Test的构造⽅法");
    }
    static{
        System.out.println("3.Test的静态块");
    }
    public static void main(String[] args) {
        System.out.println("--start--");
        new Test();
        new Test();
        System.out.println("--end--");
    }
}
