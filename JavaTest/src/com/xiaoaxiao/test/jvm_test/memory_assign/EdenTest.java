package com.xiaoaxiao.test.jvm_test.memory_assign;

/**
 * Created by xiaoaxiao on 2019/7/30
 * Description: 对象优先在Eden区（新生代中）分配
 *          当Eden区没有足够空间进行对象分配时，JVM会发生Minor GC。
 *
 *     -XX:+PrintGCDetails
 *     -XX:+UseSerialGC
 *     -Xms20M -Xms20M -Xmn10M
 *     -XX:SurvivorRatio=8
 */
public class EdenTest {

    private static final int _2MB = 1024*1024*2;

    public static void main(String[] args) {
        byte[] byte1 = new byte[_2MB];
        byte[] byte2 = new byte[_2MB];
        byte[] byte3 = new byte[_2MB];

        // 出现Minor GC
        byte[] byte4 = new byte[_2MB*2];


    }
}
