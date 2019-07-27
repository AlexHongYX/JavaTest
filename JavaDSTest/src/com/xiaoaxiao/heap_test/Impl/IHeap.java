package com.xiaoaxiao.heap_test.Impl;

/**
 * Created by xiaoaxiao on 2019/7/27
 * Description:
 */
public interface IHeap {

    // 初始化建立大根堆
    void initHeap(int[] array);
    // 向上调整，从孩子结点开始调整
    void adjustUp(int child);
    // 向下调整，从父结点开始调整
    void adjustDown(int root,int len);
    // 插入 item 到堆中
    void pushHeap(int item);
    // 返回堆顶元素，删除数据元素
    int popHeap();
    // 返回堆顶元素，不删除数据元素
    int getHeapTop();
    // 堆排序
    void heapSort();
    // 打印
    void show();

}
