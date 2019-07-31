package com.xiaoaxiao.heap_test.kth_largest;

/**
 * Created by xiaoaxiao on 2019/7/31
 * Description: LeetCode.703 数据流的第K大元素
 *          设计一个找到数据流中第K大元素的类（class）。
 *          注意是排序后的第K大元素，不是第K个不同的元素。
 *
 *          题解：
 *             抛开堆的一系列操作（入堆，出堆，查看堆顶元素，向上调整，向下调整）
 *             因为Java有已经实现好的堆PriorityQueue（直接用）
 *
 *             该题的核心：只需要得到数据流中第K大元素
 *
 *           创建一个长度为K的小根堆->该堆的堆顶元素就是整个堆的第K大元素
 *              1、初始化时（遍历传入的数组）：
 *                  ①若当前堆没满，则直接插入堆
 *                  ②若当前堆满了，则需要判断当前值与堆顶元素（第K大元素）的值
 *                      a)若大于堆顶元素，则将当前堆顶元素弹出，经该元素插入堆
 *                      b)若小于或等于堆顶元素，则直接扔掉（什么也不做，直接略过）
 *                 这一步可以省略，因为1和2的处理方式都是相同的
 *                 因此遍历传入数组，调用add方法即可
 *              2、每次添加时：
 *                    ①若当前堆没满，则直接插入堆
 *                    ②若当前堆满了，则需要判断当前值与堆顶元素（第K大元素）的值
 *                        a)若大于堆顶元素，则将当前堆顶元素弹出，经该元素插入堆
 *                        b)若小于或等于堆顶元素，则直接扔掉（什么也不做，直接略过）
 *                  或者可以将①②封装成一个方法，1,2都直接调用即可
 *              3、排到最后，当前堆的堆顶元素即为第K大元素
 *
 *              4、解释：
 *                  如果当前值比堆顶元素还大，则堆顶元素明显不会是第K大元素了，而
 *                  当前值就得插入堆中，重新经过向上/向下调整找到当前的第K大元素
 *                  作为新的堆顶
 *
 *                  第K大元素为临界值，若比该值小，则对查找第K大元素没有用处（直接略过）
 *                  若比该值大，则当前的堆顶元素肯定不再是第K大元素
 *                      （因为是小根堆，下面的元素堆顶元素大，这是再加了一个比堆顶元素大
 *                      的数，堆顶元素肯定不会是第K大元素了，因此需要重新设置堆顶元素）
 *
 *                  由于当前元素大于堆顶才会更新栈顶元素，因此重复的元素也不会进入到堆中，
 *                  保证了最终的堆顶元素一定是第K大元素
 */
public class KthLargest {

    private int[] elem;
    private int usedSize;
    private int KValue;

    // 先将数组初始化为大根堆
    public KthLargest(int k, int[] nums) {
        this.KValue = k;
        // 创建一个只有k的小根堆
        this.elem = new int[k];
        for (int i = 0; i < nums.length; i++) {
//            // 如果该小根堆能装下
//            if (i < k) {
//                push(nums[i]);
//            }else {
//                if (nums[i] > peek()) {
//                    poll();
//                    push(nums[i]);
//                }
//            }
            add(nums[i]);
        }
    }

    public int add(int val) {
        if (!isFull()){
            push(val);
        }else {
            if(val > peek()){
                poll();
                push(val);
            }
        }
        return peek();
    }


    private int peek() {
        return this.elem[0];
    }

    private void adjustDown(int root, int len) {
        int parent = root;
        int child = parent * 2 + 1;
        while (child < len) {
            if (child + 1 < len && this.elem[child + 1] < this.elem[child]) {
                child++;
            }
            if (this.elem[child] < this.elem[parent]) {
                int tmp = this.elem[child];
                this.elem[child] = this.elem[parent];
                this.elem[parent] = tmp;

                parent = child;
                child = parent * 2 + 1;
            } else {
                break;
            }
        }
    }

    private boolean isFull(){
        return this.usedSize == this.elem.length;
    }

    private void push(int val) {
        this.elem[this.usedSize] = val;
        this.usedSize++;
        adjustUp(this.usedSize-1);
    }

    private boolean isEmpty() {
        return this.usedSize == 0;
    }

    private void poll(){
        if (isEmpty()){
            return;
        }
        int tmp = this.elem[0];
        this.elem[0] = this.elem[this.usedSize-1];
        this.elem[this.usedSize-1] = tmp;
        this.usedSize--;
        adjustDown(0,this.usedSize);
    }

//    private int sortHeap() {
//        if (isEmpty()) {
//            throw new UnsupportedOperationException("堆空");
//        }
//
//        int[] helper = new int[this.usedSize];
//        for (int i = 0; i < this.usedSize; i++) {
//            helper[i] = this.elem[i];
//        }
//
//        int end = this.usedSize - 1;
//        while (end > 0) {
//            int tmp = helper[0];
//            helper[0] = helper[end];
//            helper[end] = tmp;
//
//            int len = end;
//
//            int parent = 0;
//            int child = parent * 2 + 1;
//            while (child < len) {
//                if (child + 1 < len && helper[child + 1] > helper[child]) {
//                    child++;
//                }
//                if (helper[child] > helper[parent]) {
//                    int t = helper[child];
//                    helper[child] = helper[parent];
//                    helper[parent] = t;
//
//                    parent = child;
//                    child = parent * 2 + 1;
//                } else {
//                    break;
//                }
//            }
//
//            end--;
//        }

//        System.out.print("helper数组：");
//        for (int i = 0; i < helper.length; i++) {
//            System.out.print(helper[i]+" ");
//        }
//        System.out.println();
//        System.out.println("*******************");
//        return helper[this.usedSize-endValue];
//}

    private void adjustUp(int child) {
        int parent = (child - 1) / 2;
        while (child > 0) {
            if (elem[child] < elem[parent]) {
                int tmp = this.elem[child];
                this.elem[child] = this.elem[parent];
                this.elem[parent] = tmp;

                child = parent;
                parent = (child - 1) / 2;
            } else {
                break;
            }
        }
    }

    public void show() {
//        System.out.print("原数组：");
        for (int i = 0; i < this.usedSize; i++) {
            System.out.print(this.elem[i] + " ");
        }
        System.out.println();
//        System.out.println("---------------------------");
    }
}


/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
