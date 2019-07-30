package com.xiaoaxiao.test.jvm_test.memory_assign;

/**
 * Created by xiaoaxiao on 2019/7/30
 * Description: 长期存活的对象将进入老年代（15岁）
 */
public class LongLifeTest {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {

        byte[] allocation1 = new byte[_1MB / 4];
        byte[] allocation2 = new byte[_1MB * 4];
        byte[] allocation3 = new byte[_1MB * 4];
        allocation3 = null;
        byte[] allocation4 = new byte[_1MB * 4];
    }
}
