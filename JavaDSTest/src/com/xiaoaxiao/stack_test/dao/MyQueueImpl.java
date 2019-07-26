package com.xiaoaxiao.queue_test.dao;

import com.xiaoaxiao.queue_test.Impl.IMyQueue;

/**
 * Created by xiaoaxiao on 2019/7/26
 * Description: 链式队列实现
 */
public class MyQueueImpl implements IMyQueue {

    class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    private Node front;
    private Node rear;
    private int trueSize;

    public MyQueueImpl() {
        this.front = null;
        this.rear = null;
        this.trueSize = 0;
    }

    /**
     * 判空
     */
    @Override
    public boolean empty() {
        return trueSize == 0;
    }

    /**
     * 返回队首元素，不出队
     * @return 队首元素的值
     */
    @Override
    public int peek() {
        if(empty()){
            throw new UnsupportedOperationException("栈为空");
        }
        return front.data;
    }

    /**
     * 返回队首元素，并出队
     * @return 队首元素的值
     */
    @Override
    public int poll() {
        if(empty()){
            throw new UnsupportedOperationException("栈为空");
        }
        int oldData = front.data;
        front = front.next;
        trueSize--;
        return oldData;
    }

    /**
     * 将 item 放入队列中
     * @param item
     */
    @Override
    public void add(int item) {
        Node node = new Node(item);
        if (empty()){
            front = node;
            rear = node;
        }else {
            rear.next = node;
            rear = node;
        }
        trueSize++;
    }

    @Override
    public int size() {
        return trueSize;
    }
}
