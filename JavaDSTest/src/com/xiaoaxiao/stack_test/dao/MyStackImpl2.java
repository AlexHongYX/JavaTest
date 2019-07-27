package com.xiaoaxiao.stack_test.dao;

import com.xiaoaxiao.stack_test.Impl.IMyStack;

/**
 * Created by xiaoaxiao on 2019/7/25
 * Description: 带头结点链表实现栈
 */
public class MyStackImpl2 implements IMyStack {

    class Node{
        private int data;
        private Node next;

        public Node(){
            this.data = -1;
        }

        public Node(int data){
            this.data = data;
        }
    }

    private Node head;
    private int trueSize;

    public MyStackImpl2(){
        this.head = new Node();
    }

    @Override
    public boolean empty() {
        return this.trueSize==0;
    }

    @Override
    public int peek() {
        if (empty()){
            throw new UnsupportedOperationException("栈空");
        }
        return this.head.next.data;
    }

    @Override
    public int pop() {
        if (empty()){
            throw new UnsupportedOperationException("栈空");
        }
        int data = this.head.next.data;
        // empty中已经判断了this.head.next!=null了，可以直接用this.head.next.next
        // 不会出现异常
        this.head.next = this.head.next.next;
        trueSize--;
        return data;
    }

    @Override
    public void push(int item) {
        Node node = new Node(item);
        node.next = this.head.next;
        this.head.next = node;
        trueSize++;
    }

    @Override
    public int size() {
        return trueSize;
    }
}
