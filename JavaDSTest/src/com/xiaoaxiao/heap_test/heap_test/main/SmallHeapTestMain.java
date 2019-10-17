package com.xiaoaxiao.heap_test.main;

import com.xiaoaxiao.heap_test.dao.SmallHeapImpl;

/**
 * Created by xiaoaxiao on 2019/7/29
 * Description: 小根堆测试
 */
public class SmallHeapTestMain {

    public static void main(String[] args) {
        int[] array = {7,9,2,5,10,1,4,3,6,4,2};

        SmallHeapImpl smallHeap = new SmallHeapImpl();

        smallHeap.initHeap(array);
//        smallHeap.show();

        System.out.println(smallHeap.popHeap());
        smallHeap.show();

        smallHeap.pushHeap(1);
        smallHeap.show();

        System.out.println(smallHeap.getHeapTop());
        smallHeap.show();

        smallHeap.heapSort();
        smallHeap.show();
    }
}
