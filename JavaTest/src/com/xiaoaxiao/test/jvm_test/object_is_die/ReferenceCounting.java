package com.xiaoaxiao.test.jvm_test.object_is_die;

/**
 * Created by xiaoaxiao on 2019/7/29
 * Description: 引用计数法的BUG，无法解决循环引用的问题
 */
public class ReferenceCounting {

    public Object instance = null;
    private static int _1MB = 1024*1024;
    private byte[] bigSize = new byte[_1MB];

    public static void main(String[] args) {
        // 计数器+1
        ReferenceCounting test1 = new ReferenceCounting();
        ReferenceCounting test2 = new ReferenceCounting();

        // 计数器+1
        test1.instance = test2;
        test2.instance = test1;

        // 计数器-1
        test1 = null;
        test2 = null;

        // 强制GC
        System.gc();
    }
}
