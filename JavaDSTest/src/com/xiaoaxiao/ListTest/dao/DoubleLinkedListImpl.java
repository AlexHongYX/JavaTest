package com.xiaoaxiao.ListTest.dao;

import com.xiaoaxiao.ListTest.Impl.IDoubleLinked;

/**
 * Created by xiaoaxiao on 2019/7/18
 * Description: 不带头结点双向链表实现
 */
public class DoubleLinkedListImpl implements IDoubleLinked {

    // 不带头结点
    class Node{

        private int data;   //数据
        private Node prev;  //前驱
        private Node next;  //后继

        public Node(int data){
            this.data = data;
        }
    }

    // 假“头结点”
    private Node head;

    // 设置一个假的“尾结点”
    private Node last;
    public DoubleLinkedListImpl(){
        this.head = null;
        this.last = null;
    }

    /**
     * 头插
     * @param data
     */
    @Override
    public void addFirst(int data) {

        Node node = new Node(data);

        // 第一次插入（head和last都是node）
        // 第一次插入的结点就是整个链表的尾结点
        if(head==null){
            head = node;
            last = node;
        }else { // 之后插入，last就不变了,只需要变head就行
            node.next = this.head;
            this.head.prev = node;
//            node.prev = null;    可以不要，本身新建一个node的prev和next都是null
            this.head = node;
        }
    }

    @Override
    public void addLast(int data) {

    }

    @Override
    public boolean addindex(int index, int data) {
        return false;
    }

    @Override
    public boolean contains(int key) {
        return false;
    }

    @Override
    public int remove(int key) {
        return 0;
    }

    @Override
    public void removeAllKey(int key) {

    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public void display() {

    }

    @Override
    public void clear() {

    }
}
