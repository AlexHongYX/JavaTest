package com.xiaoaxiao.heap_test.main;

import com.xiaoaxiao.heap_test.dao.HeapImpl;

/**
 * Created by xiaoaxiao on 2019/7/27
 * Description:
 */
public class TestMain {

    public static void main(String[] args) {

        int[] array = {1,2,3,4,5,6,7,8,9,10};
        int[] array2 = {10,9,8,6,7,5,4,3,2,4,2};
        HeapImpl heap = new HeapImpl();

//        heap.initHeap(array);
//        heap.show();
//
//        heap.pushHeap(11);
//        heap.show();
//
//        heap.popHeap();
//        heap.show();

        heap.initHeap(array2);
        heap.show();

        System.out.println(heap.popHeap());
        heap.show();

        System.out.println(heap.getHeapTop());

        heap.heapSort();
        heap.show();
    }
}
