package com.xiaoaxiao.test.jvm_test.volatile_test;

/**
 * Created by xiaoaxiao on 2019/8/3
 * Description: ActiveCount测试，在IDEA下分别以RUn和Debug模式跑，结果不一样
 */
public class ActiveCountTest {

    public static void main(String[] args) {
        System.out.println("thread.activeCount = "+Thread.activeCount());
    }
}
