package com.xiaoaxiao.heap_test.kth_largest;

import java.util.PriorityQueue;

/**
 * Created by xiaoaxiao on 2019/7/31
 * Description: 基于PriorityQueue<优先级队列>模拟的小根堆
 */
public class KthLargestSimple {

    private PriorityQueue<Integer> priorityQueue;
    private final int KValue;

    public KthLargestSimple(int k, int[] nums) {
        KValue = k;
        priorityQueue = new PriorityQueue<>(KValue);
        for (int num:nums){
            insert(num);
        }
    }

    public int add(int val) {
        insert(val);
        return priorityQueue.peek();
    }

    public void insert(int val) {
        if (priorityQueue.size() < KValue){
            priorityQueue.add(val);
        }else {
            if (val > priorityQueue.peek()){
                priorityQueue.poll();
                priorityQueue.add(val);
            }
        }
    }
}
