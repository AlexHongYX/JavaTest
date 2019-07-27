package com.xiaoaxiao.heap_test.dao;

import com.xiaoaxiao.heap_test.Impl.IHeap;

import java.util.Arrays;

/**
 * Created by xiaoaxiao on 2019/7/27
 * Description: 堆测试
 */
public class HeapImpl implements IHeap {

    private int[] elem;
    private int usedSize;
    private static final int DEFAULT_SIZE = 20;

    public HeapImpl() {
        this.elem = new int[DEFAULT_SIZE];
        this.usedSize = 0;
    }

    /**
     * 创建一个大根堆
     * <p>
     * 一定要注意传入adjustDown的usedSize和adjustDown函数中的逻辑处理一一对应
     *
     * @param array
     */
    @Override
    public void initHeap(int[] array) {
        // 接收一个数组
        for (int i = 0; i < array.length; i++) {
            this.elem[i] = array[i];
            this.usedSize++;
        }

        // 将该数组初始化为大根堆
        // 1、先找到最后一个结点的父节点（最后一棵树的根节点）
        // 2、由该父结点依次向上找，每经过一个结点就向下调整为大根堆
        // 3、调整大根堆，最起码得是个二叉树，最起码子树要有根节点，
        //      所以直接从最后一棵树向上找
        for (int i = (usedSize - 2) / 2; i >= 0; i--) {
            adjustDown(i, usedSize);
        }
    }

    /**
     * 根据根节点和子节点可以到达数组的最大限，将当前子树向下调整为大根堆
     *
     * @param root 当前子树的根节点
     * @param len  子结点最大可达到的范围
     *             简单起见：len直接等于usedSize（子结点的位置不会超过数组的长度）
     *             即便当前子树根节点在0号位置，也很有可能需要一级一级向下调整
     *             直到调整到子结点大于或等于usedSize时（循环向下调整的退出条件）
     *             <p>
     *             已知父节点p，子结点c1=2*p+1,c2=2*p+2（可能不存在，最后一颗子树）
     *             已知子结点c，父结点p=(c-1)/2
     *             <p>
     *             1、每次判断当前子树的根节点，与当前子树左右节点中较大值的结点进行比较
     *             若根>较大值，则该子树已是最大根，直接退出
     *             若根<较大值，将根的值与较大结点的值交换
     *             2、再对当前被交换的子节点为根的子树进行下一轮的大根堆调整
     *             直到调整到最后一个子树（子节点超过usedSize了）即完成一个子树的向下调整
     */
    @Override
    public void adjustDown(int root, int len) {
        int parent = root;
        // child存储的是左右子节点中最大的值
        int child = root * 2 + 1;

        // 临界条件时child<len，若大于或等于就超出数组范围了
        // 说明已经调整完成
        while (child < len) {
            // 如果右子节点存在并且大于左子节点，则child应该指向右子节点
            if (child + 1 < len && elem[child] < elem[child + 1]) {
                child++;
            }
            // 经过上面的处理，当前的child已经是指左右节点中较大的那个结点了
            // 判断child指向的值与parent指向的值的大小关系
            // 若elem[child]>elem[parent]，说明该子树需要被继续向下调整
            if (elem[child] > elem[parent]) {
                // 交换child与parent所指向的值
                int tmp = elem[child];
                elem[child] = elem[parent];
                elem[parent] = tmp;
                // 这次调整很可能会导致下面子树不是大根堆，因此需要继续向下调整
                // 将此时被交换的子节点作为新的父节点
                parent = child;
                // 将被交换的子节点的左子节点作为新的子节点
                child = parent * 2 + 1;

            } else {
                // 若elem[child]<=elem[parent]
                // 说明该子树已经是个大根堆了，无需再调整，直接退出循环
                break;
            }
        }
    }

    /**
     * 由于新加入了当前子节点，破坏了大根堆的结构，因此以该节点为起点向上调整整个大根堆
     * 直到孩子结点走到了最上面（0号位置）为止
     * 一定得是孩子结点走到最上面，不能是父节点，万一最后一次，当父节点在最上面，此时孩子结点和父节点可以交换就出错了
     * 当孩子结点走到最上面，一定代表已经走到最上面了。
     *
     * @param child
     */
    @Override
    public void adjustUp(int child) {
        int parent = (child - 1) / 2;
        while (child > 0) {
            if (elem[child] > elem[parent]) {
                int tmp = elem[child];
                elem[child] = elem[parent];
                elem[parent] = tmp;

                // 这次调整很可能会导致上面子树不是大根堆，因此需要继续向上调整（child和parent都往上移）
                child = parent;
                parent = (child - 1) / 2;
            } else {
                break;
            }
        }
    }

    public boolean isFull() {
        return this.usedSize == this.elem.length;
    }


    /**
     * 添加元素
     * 1、因为是使用数组存储，所以先判断当前数组的容量还能否进行插入操作（usedSize和elem.length）
     * 若容量够就插入，
     * 若容量不够就进行扩容（Arrays.copyOf(oldElem,newLength)
     * 2、插入的前提是，当前数组已经是一个大根堆了
     *
     * @param item
     */
    @Override
    public void pushHeap(int item) {
        if (isFull()) {
            this.elem = Arrays.copyOf(this.elem, this.elem.length * 2);
        }
        // 将当前元素放在数组最后一个位置（elem[useSize])，并将useSize++
        this.elem[this.usedSize] = item;
        this.usedSize++;

        // 由于新元素的添加，导致大根堆结构遭到破坏，所以，以该位置为起点，向上调整大根堆
        // 由于usedSize++了，所以当前数组中的最后一个元素（才添加的元素）的下标为useSize-1
        adjustUp(usedSize - 1);
    }

    public boolean isEmpty() {
        return this.usedSize == 0;
    }

    /**
     * 删除堆顶数据
     * 1、删除堆顶数据首先要判断数组是否为空
     * 若为空，则抛异常
     * 若不空，执行下面步骤
     * 2、先将堆顶数据与最后一个数据交换
     * 3、再将最后一个数据删除（usedSize--）
     * 4、由于交换到堆顶的数据可能会导致大根堆结构遭到破坏（除了堆顶，其他地方还保持着大根堆结构）
     * 因此需要对当前堆顶这一处进行向下调整，再次实现大根堆
     * <p>
     * 一定要注意传入adjustDown的usedSize和adjustDown函数中的逻辑处理一一对应
     * <p>
     * 删除的前提是，当前数组已经是一个大根堆了
     *
     * @return
     */
    @Override
    public int popHeap() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("堆为空");
        }
        // 先交换堆顶数据与最后一个数据
        int tmp = this.elem[0];
        this.elem[0] = this.elem[this.usedSize - 1];
        this.elem[this.usedSize - 1] = tmp;

        // 删除最后一个结点
        this.usedSize--;

        // 将堆顶进行向下调整
        // 下标的最大的限度是 小于当前已有的数据个数（usedSize）
        adjustDown(0, this.usedSize);
        // 将tmp（当前堆顶元素）返回
        return tmp;
    }

    /**
     * 1、判空
     * 2、若不空，返回堆顶元素即可(elem[0])
     *
     * @return
     */
    @Override
    public int getHeapTop() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("堆为空");
        }
        return this.elem[0];
    }

    /**
     * 堆排序，将堆变为顺序的
     * 1、将堆顶元素与最后一个结点元素交换
     * 2、将堆顶元素进行向下调整，参数中临界值len为end
     * 3、当前最后一个结点已经是整个数组中最大的值（已排好序），将end--，更新一下当前的最后一个结点
     * 4、再次进入循环判断
     *
     * 排序的前提是，当前数组已经是一个大根堆了
     *
     *
     * 一定要注意传入adjustDown的end和adjustDown函数中的逻辑处理一一对应
     *
     *  自己初始化时已经将当前数组变为大根堆了
     *  但以后写的时候，要先将普通数组处理为大根堆，再进行堆排序
     *
     *  时间复杂度O(nlogn)
     *      for循环（处理普通数组）：O(n)
     *      while是O(n)
     *      while中的adjustDown是O(logn)，与高度相关
     *  空间复杂度：O(1)
     *  稳定性：不稳定
     */
    @Override
    public void heapSort() {
        // 先记录当前最后一个结点
        int end = this.usedSize - 1;
        while (end > 0){
            // 交换
            int tmp = this.elem[0];
            this.elem[0] = this.elem[end];
            this.elem[end] = tmp;

            // 堆顶向下调整
            adjustDown(0,end);
            end--;
        }
    }

    @Override
    public void show() {
        for (int i = 0; i < this.usedSize; i++) {
            System.out.print(elem[i] + " ");
        }
        System.out.println();
    }
}
