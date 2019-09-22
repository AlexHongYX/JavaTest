package com.xiaoaxiao.test.object_and_string_test;

/**
 * Created by xiaoaxiao on 2019/9/22
 * Description: Integer的"缓存值"(常量池)概念
 *              [-128,127]
 */
public class IntegerTest {

    public static void main(String[] args) {
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 1000;
        Integer i4 = 1000;

        System.out.println(i1==i2);
        System.out.println(i3==i4);
    }
}
