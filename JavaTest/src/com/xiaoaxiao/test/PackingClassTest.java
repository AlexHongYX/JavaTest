package com.xiaoaxiao.test;

/**
 * Created by xiaoaxiao on 2019/7/8
 * Description: 包装类，手工拆装箱，JDK1.5后就自动拆装箱了
 */
public class PackingClassTest {
    public static void main(String[] args){

        // 手工装箱
        Integer i = new Integer(10);

        // 手工拆箱
        int ret = i.intValue();

        // 自动装箱
        Integer i2 = 20;

        // 自动拆箱
        int ret2 = i2+20;

        System.out.println(ret+1);
    }
}
