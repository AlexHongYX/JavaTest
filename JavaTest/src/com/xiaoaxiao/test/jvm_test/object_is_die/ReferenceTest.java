package com.xiaoaxiao.test.jvm_test.object_is_die;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created by xiaoaxiao on 2019/7/29
 * Description: WeakReference（弱引用）
 *              当垃圾回收器开始工作时，无论当前内存是否够用，都会回收掉仅被弱引用关联的对象
 *              Soft
 */
public class ReferenceTest {

    private static int _1MB = 1024*1024;
    private byte[] bigSize = new byte[_1MB];

    public static void main(String[] args) {
        ReferenceTest referenceTest = new ReferenceTest();

        WeakReference weakReference = new WeakReference(referenceTest);

//        SoftReference softReference = new SoftReference(referenceTest);

        referenceTest = null;

        System.gc();
    }
}
