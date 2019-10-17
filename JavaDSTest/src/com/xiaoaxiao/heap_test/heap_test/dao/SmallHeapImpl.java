package com.xiaoaxiao.heap_test.dao;

import com.xiaoaxiao.heap_test.Impl.IHeap;

import java.util.Arrays;

/**
 * Created by xiaoaxiao on 2019/7/29
 * Description:
 */
public class SmallHeapImpl implements IHeap {

    private static final int DEFAULT_SIZE = 20;
    private int[] elem;
    private int usedSize;

    public SmallHeapImpl() {
        this.elem = new int[DEFAULT_SIZE];
        this.usedSize = 0;
    }

    /**
     * 接收一个普通数组，先将该数组拷贝一份，再将其变为小根堆
     *
     * @param array
     */
    @Override
    public void initHeap(int[] array) {

        for (int i = 0; i < array.length; i++) {
            this.elem[i] = array[i];
            this.usedSize++;
        }

        // 从最后一颗子树一次往前进行向下调整
        for (int i = (this.usedSize - 2) / 2; i >= 0; i--) {
            adjustDown(i, this.usedSize);
        }

    }

    @Override
    public void adjustUp(int child) {
        int parent = (child - 1) / 2;
        // 循环条件一定得是child>0，不能是parent，那样会少计算一次
        while (child > 0) {
            if (this.elem[child] < this.elem[parent]) {
                int tmp = this.elem[child];
                this.elem[child] = this.elem[parent];
                this.elem[parent] = tmp;

                // child、parent往上移
                child = parent;
                parent = (child - 1) / 2;
            } else {
                break;
            }
        }
    }

    // 设置子树最深的边界
    // 参数len代表的是长度，所以child的值肯定会小于len的
    // 数组的取值为：[0,len-1]或者[0,len)
    @Override
    public void adjustDown(int root, int len) {
        int parent = root;
        // 先将child设置为左子树，找两颗子树中小的那一颗
        int child = parent * 2 + 1;

        while (child < len) {
            // 每次都需要对左右子树进行判断，所以得放在循环里面
            if (child + 1 < len && elem[child + 1] < elem[child]) {
                child++;
            }
            // 由于是小根堆，如果elem[child]比elem[parent]小，则需要交换
            if (elem[child] < elem[parent]) {
                int tmp = elem[child];
                elem[child] = elem[parent];
                elem[parent] = tmp;
                // 继续向下走
                parent = child;
                child = parent * 2 + 1;
            } else {
                break;
            }
        }
    }

    private boolean isFull() {
        return this.usedSize == this.elem.length;
    }

    // 插入之前先判满
    @Override
    public void pushHeap(int item) {
        if (isFull()) {
            this.elem = Arrays.copyOf(this.elem, this.elem.length * 2);
        }
        // 先插入到最后，再向上调整
        this.elem[this.usedSize] = item;
        this.usedSize++;
        adjustUp(this.usedSize - 1);
    }

    private boolean isEmpty() {
        return this.usedSize == 0;
    }

    // 弹出堆顶元素，先判空
    @Override
    public int popHeap() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("堆以空");
        }
        // 将堆顶元素与最后一个元素交换
        int tmp = this.elem[0];
        this.elem[0] = this.elem[this.usedSize - 1];
        this.elem[this.usedSize - 1] = tmp;

        // 先将最后一个元素移除
        this.usedSize--;

        // 再将当前栈顶的元素向下调整
        adjustDown(0, this.usedSize);
        return tmp;
    }

    @Override
    public int getHeapTop() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("堆空");
        }
        return this.elem[0];
    }

    // 堆排序（从大到小）
    // 堆顶和最后一个元素交换，将当前的堆顶向下调整,再将end往前走一个，
    // 循环即可
    @Override
    public void heapSort() {
        int end = usedSize - 1;
        while (end > 0){
            // 交换
            int tmp = this.elem[0];
            this.elem[0] = this.elem[end];
            this.elem[end] = tmp;

            // end此时刚好代表除此时最后一个元素外前面数组的长度
            adjustDown(0,end);

            end--;
        }
    }

    @Override
    public void show() {
        for (int i = 0; i < this.usedSize; i++) {
            System.out.print(this.elem[i] + " ");
        }
        System.out.println();
        System.out.println("================================");
        System.out.println();
    }
}
