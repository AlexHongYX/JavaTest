package com.xiaoaxiao.queue_test.circular_queue;

/**
 * Created by xiaoaxiao on 2019/7/26
 * Description: 循环队列实现 LeetCode.622
 */
class MyCircularQueue {

    private int[] elem;
    private int front;
    private int rear;

    /** Initialize your data structure here. Set the size of the queue to be k.
             实际上必须得是k个元素，因为有一个位置是空的，所以需要设置数组大小为k+1 */
    public MyCircularQueue(int k) {
        this.elem = new int[k+1];
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()){
            return false;
        }
        elem[this.rear] = value;
        this.rear = (this.rear+1)%this.elem.length;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()){
            return false;
        }
        this.front = (this.front+1)%this.elem.length;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()){
            return -1;
        }
        return this.elem[this.front];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()){
            return -1;
        }
        int index = this.rear==0?this.elem.length-1:this.rear-1;
        return this.elem[index];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return this.rear==this.front;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return (this.rear+1)%this.elem.length==this.front;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
