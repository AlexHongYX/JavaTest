package com.xiaoaxiao.heap_test.dao;

import com.xiaoaxiao.heap_test.Impl.IHeap;

import java.util.Arrays;

/**
 * Created by xiaoaxiao on 2019/7/30
 * Description: 反复写大根堆
 */
public class TestImpl implements IHeap {

    private int[] elem;
    private static final int DEFAULT_SIZE = 20;
    private int usedSize;

    public TestImpl() {
        this.elem = new int[DEFAULT_SIZE];
        this.usedSize = 0;
    }

    @Override
    public void initHeap(int[] array) {
        for (int i = 0; i < array.length; i++) {
            this.elem[i] = array[i];
            this.usedSize++;
        }
        for (int i = (this.usedSize - 1) / 2; i >= 0; i--) {
            adjustDown(i, this.usedSize);
        }
    }

    @Override
    public void adjustUp(int child) {
        int parent = (child - 1) / 2;

        while (child > 0) {
            if (this.elem[child] > this.elem[parent]){
                int tmp = this.elem[child];
                this.elem[child] = this.elem[parent];
                this.elem[parent] = tmp;

                child = parent;
                parent = (child-1)/2;
            }else {
                break;
            }
        }
    }

    @Override
    public void adjustDown(int root, int len) {
        int parent = root;
        int child = (parent * 2) + 1;

        while (child < len) {
            if (child + 1 < len && this.elem[child + 1] > this.elem[child]) {
                child++;
            }
            if (this.elem[child] > this.elem[parent]) {
                int tmp = this.elem[child];
                this.elem[child] = this.elem[parent];
                this.elem[parent] = tmp;

                parent = child;
                child = (parent * 2) + 1;
            } else {
                break;
            }
        }
    }

    private boolean isFull(){
        return this.usedSize == this.elem.length;
    }

    @Override
    public void pushHeap(int item) {
        if (isFull()){
            this.elem = Arrays.copyOf(this.elem,this.elem.length*2);
        }
        this.elem[this.usedSize] = item;
        this.usedSize++;
        adjustUp(this.usedSize - 1);
    }

    private boolean isEmpty(){
        return this.usedSize==0;
    }

    @Override
    public int popHeap() {
        if (isEmpty()){
            throw new UnsupportedOperationException("堆为空");
        }
        int tmp = this.elem[0];
        this.elem[0] = this.elem[this.usedSize - 1];
        this.elem[this.usedSize - 1] = tmp;

        this.usedSize--;

        adjustDown(0,this.usedSize);
        return tmp;
    }

    @Override
    public int getHeapTop() {
        if (isEmpty()){
            throw new UnsupportedOperationException("堆为空");
        }
        return this.elem[0];
    }

    @Override
    public void heapSort() {
        if (isEmpty()){
            throw new UnsupportedOperationException("堆为空");
        }
        int end = this.usedSize-1;
        while (end > 0){
            int tmp = this.elem[0];
            this.elem[0] = this.elem[end];
            this.elem[end] = tmp;

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
    }
}
