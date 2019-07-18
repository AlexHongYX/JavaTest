package com.xiaoaxiao.ListTest.dao;

import com.xiaoaxiao.ListTest.Impl.ICLinkList;

/**
 * Created by xiaoaxiao on 2019/7/18
 * Description: 带头结点的循环单链表
 */
public class CHeadSingleListImpl implements ICLinkList {

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

    // head属性
    private Node head;

    public CHeadSingleListImpl(){
        this.head = new Node();

        // 即便是只有头结点，也需要成为循环单链表，自己指向自己
        // 方便于以后进行头插
        this.head.next = this.head;
    }

    /**
     * 头插
     */
    @Override
    public void addFirst(int data) {
        Node node = new Node(data);
        node.next = this.head.next;
        this.head.next = node;
    }

    /**
     * 尾插
     * 先找尾巴 tail.next = head;
     */
    @Override
    public void addLast(int data) {
        Node newNode = new Node(data);

        Node node = this.head;

        // 因为是成环的
        while (node.next!=this.head){
            node = node.next;
        }

        newNode.next = node.next;
        node.next = newNode;
    }

    /**
     * 检查传入的index是否合法
     */
    private void checkIndex(int index){
        if (index<0 || index>getLength()){
            throw new IndexOutOfBoundsException("下标不合法");
        }
    }

    /**
     * 第i个结点插入（从0开始）
     * 插入到底i个结点处，原本的第i个结点移到i+1去
     * 所以cur走到第i-1个结点处就行了
     */
    @Override
    public boolean addIndex(int index, int data) {

        checkIndex(index);

        Node cur = this.head;

        for (int i=0;i<index;i++){
            cur = cur.next;
        }

        Node node = new Node(data);

        node.next = cur.next;
        cur.next = node;

        return true;
    }

    /**
     * 链表中是否包含某个值为key的结点
     */
    @Override
    public boolean contains(int key) {

        // 将cur初始化为head.next，遍历的结束条件就是cur==head（刚好转完一圈）
        Node cur = this.head.next;

        while (cur!=head){
            if(cur.data == key){
                return true;
            }
            cur = cur.next;
        }

        return false;

    }

    /**
     * 找key前驱
     */
    private Node searchPre(int key){
        Node pre = this.head;

        while (pre.next!=head){
            if(pre.next.data==key){
                return pre;
            }
            pre = pre.next;
        }

        return null;
    }

    /**
     * 删除第key个结点，先找第key个结点的前驱
     * @return 返回的是被删除的值
     */
    @Override
    public int remove(int key) {
        Node pre = searchPre(key);

        if(pre==null){
            throw new UnsupportedOperationException("key不存在");
        }

        int oldData;

        Node delNode = pre.next;
        // 使用oldNode保存被删除结点的值
        oldData = delNode.data;

        pre.next = delNode.next;

        return oldData;
    }

    /**
     * 删除链表中所有等于key值的结点
     * 问题1:     1 2 3 3 3 4
     * 问题2：    1 2 3 3 3
     * 问题3：    3 3 3 3 3
     * 所以必须的引入pre（前一个结点）
     */
    @Override
    public void removeAllKey(int key) {
        Node pre = this.head;
        Node cur = this.head.next;

        // 必须是cur!=head，若是cur.next!=head，那么最后一个结点就取不到了
        while (cur!=head){

            if(cur.data==key){
                pre.next = cur.next;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }
    }

    /**
     * 判断链表长度
     */
    @Override
    public int getLength() {
        Node cur = this.head.next;
        int count = 0;
        while(cur!=this.head){
            count++;
        }
        return count;
    }

    /**
     * 打印链表
     */
    @Override
    public void display() {
        Node cur = this.head.next;

        while (cur!=head){
            System.out.print(cur.data+" ");
            cur = cur.next;
        }
        System.out.println();
    }

    /**
     * 清空链表（所有的结点都不要被其他结点指向，便于GC清除）
     */
    @Override
    public void clear() {

        // 直到head.next = head，意味着只剩下一个头结点了
        // head肯定不能动
        while (this.head.next != this.head){
            Node cur = this.head.next;
            // 当前的cur没有指向，被GC清除
            this.head.next = cur.next;
            cur.next = null;
        }

        //  把head清楚
        head.next = null;
    }
}
