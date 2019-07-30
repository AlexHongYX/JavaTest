package com.xiaoaxiao.test.jvm_test.memory_assign;

/**
 * Created by xiaoaxiao on 2019/7/30
 * Description: 大对象直接进入老年代
 *
 *  -verbose:gc
 *  -Xms20M
 *  -Xmx20M
 *  -Xmn10M
 *  -XX:+PrintGCDetails
 *  -XX:SurvivorRatio=8
 *  -XX:PretenureSizeThreshold=3145728
 */
public class BigObjectTest {

    public static void main(String[] args) {
        byte[] allocation;
        allocation = new byte[4*1024*1024];  //直接分配在老年代
    }
}
