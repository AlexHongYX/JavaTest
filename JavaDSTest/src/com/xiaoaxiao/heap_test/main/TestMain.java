package com.xiaoaxiao.heap_test.main;

import com.xiaoaxiao.heap_test.dao.TestImpl;

/**
 * Created by xiaoaxiao on 2019/7/30
 * Description:
 */
public class TestMain {

    public static void main(String[] args) {
        int[] array2 = {10,9,8,6,7,5,4,3,2,4,2};
        TestImpl heap = new TestImpl();


        heap.initHeap(array2);
        heap.show();

        System.out.println(heap.popHeap());
        heap.show();

        System.out.println(heap.getHeapTop());

        heap.pushHeap(7);
        heap.show();

        heap.heapSort();
        heap.show();

    }
}
