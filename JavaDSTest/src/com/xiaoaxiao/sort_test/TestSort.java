package com.xiaoaxiao.sort_test;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * Created by xiaoaxiao on 2019/9/19
 * Description:
 */
public class TestSort {

    public static void main(String[] args) {
//        int[] array = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        int[] array = new int[100000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {            // 设置数组的值为[1-100000]中的随机数
            array[i] = random.nextInt(100000) + 1;
        }
        System.out.println(Arrays.toString(array));
//        insertSort(array);
//        shellSort(array);
//        selectSort(array);
//        bubbleSort(array);
//        System.out.println(partion(array,0,array.length-1));\
//        quickSort(array);
//        quickSort2(array);
        long startTime = System.currentTimeMillis();
        // 递归中不要创建太大的空间，因此将tmpArray在主函数中创建
//        int[] tmpArray = new int[array.length];
//        mergeSort(array, 0, array.length - 1, tmpArray);
        mergeSort2(array);
        long endTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(array));
        System.out.println("共耗时：" + (endTime - startTime) + "ms");
    }

    /**
     * 直接插入排序
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     * 稳定性：稳定
     *
     * @param array 形参数组
     */
    private static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i - 1;
            int tmp = array[i];
            // 退出条件有两个
            //      1、找到第一个比array[i]小的元素(该元素前面的元素都小于array[i]——已排好序)
            //      2、j需要在数组下标范围内
            //      3、要先判断j>=0再判断array[j]>tmp，否则如果array[0]>array[i],j--了，此时应该先判断j>=0，
            //                              否则会出现数组下标越界异常(此时j==-1，应该依靠j>=0使其退出循环)
            // 由于此处array[j]>tmp，因此直接插入是稳定的，如果array[j]==tmp并不会进入循环
            while (j >= 0 && array[j] > tmp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = tmp;
        }
    }

    /**
     * 希尔(shell)排序：直接插入排序的优化
     * 时间复杂度：O(n^1.3~n^2)
     * 空间复杂度：O(1)
     * 稳定性：不稳定(进行了跳跃式的交换)
     *
     * @param array 形参数组
     */
    private static void shellSort(int[] array) {
        int[] drr = {5, 3, 1};
        for (int i = 0; i < drr.length; i++) {
            shell(array, drr[i]);
        }
    }

    /**
     * shell排序的具体实现
     *
     * @param array 形参数组
     * @param gap   跳跃间隔
     */
    private static void shell(int[] array, int gap) {
        for (int i = gap; i < array.length; i++) {
            int j = i - gap;
            int tmp = array[i];
            while (j >= 0 && array[j] > tmp) {
                array[j + gap] = array[j];
                j -= gap;
            }
            array[j + gap] = tmp;
        }
    }

    /**
     * 直接选择排序
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     * 稳定性：不稳定
     *
     * @param array 形参数组
     */
    private static void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    int tmp = array[j];
                    array[j] = array[i];
                    array[i] = tmp;
                }
            }
        }
    }

    /**
     * 冒泡排序
     * 时间复杂度：O(n²),优化后：O(n)
     * 空间复杂度：O(1)
     * 稳定性：稳定(没有进行跳跃式交换)
     *
     * @param array 形参数组
     */
    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {   // i代表的是趟数(5个数比较4次即可)
            boolean flag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    flag = false;
                }
            }
            // 如果第i趟没有进行交换，证明已经有序，之后就不用再执行了。直接break
            if (flag) {
                break;
            }
        }
    }

    /**
     * 快速排序
     * 时间复杂度：O(NlogN)
     * 最好：O(nlogn)   最坏：O(n^2)
     * 空间复杂度：O(logN)
     * 最好：O(logN)    最坏：O(n)->有序时
     * 稳定性：不稳定(有跳跃式的交换)
     *
     * @param array 形参数组
     */
    private static void quickSort(int[] array) {
        quick(array, 0, array.length - 1);
    }

    private static void quick(int[] array, int start, int end) {

        // 若递归到小的子区间时，可以考虑直接插入排序
        if (end - start + 1 < 16) {
            insertSort2(array, start, end);
        }

        // 先三数取中
        medianOfThree(array, start, end);
        int par = partion(array, start, end);
        // 递归左边
        // 保证左边的元素个数>=2，若是1个就已经有序了
        if (par > start + 1) {
            quick(array, start, par - 1);
        }
        if (par < end - 1) {
            quick(array, par + 1, end);
        }
    }

    /**
     * 非递归快速排序
     *
     * @param array 形参数组
     */
    private static void quickSort2(int[] array) {
        Stack<Integer> stack = new Stack<>();
        int low = 0;
        int high = array.length - 1;
        int par = partion(array, low, high);
        if (par > low + 1) {
            stack.push(low);
            stack.push(par - 1);
        }
        if (par < high - 1) {
            stack.push(par + 1);
            stack.push(high);
        }

        while (!stack.empty()) {
            high = stack.pop();
            low = stack.pop();
            par = partion(array, low, high);
            if (par > low + 1) {
                stack.push(low);
                stack.push(par - 1);
            }
            if (par < high - 1) {
                stack.push(par + 1);
                stack.push(high);
            }
        }
    }

    private static void insertSort2(int[] array, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int j = i - 1;
            int tmp = array[i];
            while (j >= start && array[j] > tmp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = tmp;
        }
    }

    /**
     * 一趟快速排序
     *
     * @param array 形参数组
     * @param low   开始下标位置
     * @param high  结束下标位置
     * @return 返回一次快排后的基准位置
     */
    private static int partion(int[] array, int low, int high) {
        int tmp = array[low];
        // 一轮做两个事，先让high走后往前走，再让low从前往后走
        while (low < high) {
            // -的过程需要控制范围low<high
            while (low < high && array[high] >= tmp) {
                high--;
            }
            // 由于从上面的while循环退出有两种可能
            //      1、low<high但array[high]<tmp，将high对应的值赋给low对应的值
            //      2、low>=high，直接退出即可(本次快排结束)
            // 所以要分别讨论两种可能
            if (low < high) {
                array[low] = array[high];
            } else {
                break;
            }

            while (low < high && array[low] <= tmp) {
                low++;
            }
            if (low < high) {
                array[high] = array[low];
            } else {
                break;
            }
        }
        array[low] = tmp;
        return low;
    }

    /**
     * 三数取中
     * array[mid] <= array[low] <= array[high]
     *
     * @param array 形参数组
     * @param low   low下标
     * @param high  high下标
     */
    private static void medianOfThree(int[] array, int low, int high) {
        int mid = (low + high) / 2;
        if (array[mid] > array[high]) {
            int tmp = array[mid];
            array[mid] = array[high];
            array[high] = tmp;
        }
        if (array[low] > array[high]) {
            int tmp = array[low];
            array[low] = array[high];
            array[high] = tmp;
        }

        if (array[low] < array[mid]) {
            int tmp = array[low];
            array[low] = array[mid];
            array[mid] = tmp;
        }
    }

    /**
     * 递归归并排序
     *
     * @param array 形参数组
     */
    private static void mergeSort(int[] array, int start, int end, int[] tmpArray) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(array, start, mid, tmpArray);
        mergeSort(array, mid + 1, end, tmpArray);
        merge(array, start, mid, end, tmpArray);
    }

    /**
     * 递归排序算法的实现
     *
     * @param array    形参数组
     * @param start    归并开始位置
     * @param mid      归并中间位置(第一个归并段的尾)
     * @param end      归并结束位置
     * @param tmpArray 不要在递归中创建过大的数组
     */
    private static void merge(int[] array, int start, int mid, int end, int[] tmpArray) {
//        尽量不要再递归的过程中创建数组(尤其是这么大的数组)
//        int[] tmpArray = new int[array.length];
        int tmpIndex = start;
        int s2 = mid + 1;
        // 先保存一下start的值，后面会用到
        int oldStart = start;

        // 保证有两个归并段
        while (start <= mid && s2 <= end) {
            if (array[start] <= array[s2]) {
                tmpArray[tmpIndex++] = array[start++];
            } else {
                tmpArray[tmpIndex++] = array[s2++];
            }
        }

        // 以下两个while对应上面while跳出的三种可能性
        //      1、start>mid
        //      2、根本没进上面的循环(只有一个归并段)
        //      3、s2>end

        // 若第一个归并段[start,mid]还没走完，或者s2越界，只有第一个归并段的情况
        while (start <= mid) {
            tmpArray[tmpIndex++] = array[start++];
        }
        // 若第二个归并段[s2,end]还没走完
        while (s2 <= end) {
            tmpArray[tmpIndex++] = array[s2++];
        }

        // 将tmp中的内容复制到array中
        // 此时必须使用循环一个一个对元素进行赋值，而不能用一些复制函数(Arrays.copyOf()..)
        // 因为此处的array只是一个数组的引用，如果使用复制函数相当于是改变了形参array的引用
        // 而指向了另一块与tmpArray元素完全相同的存储地址
        for (int i = oldStart; i <= end; i++) {
            array[i] = tmpArray[i];
        }
    }

    /**
     * 非递归归并排序
     *
     * @param array
     */
    private static void mergeSort2(int[] array) {
        for (int i = 1; i < array.length; i *= 2) {
            merge2(array, i);
        }
    }

    /**
     * 非递归归并排序的具体实现
     *
     * @param array 形参数组
     * @param gap   每一个归并段的长度
     */
    private static void merge2(int[] array, int gap) {

        int[] tmpArray = new int[array.length];
        int tmpIndex = 0;

        int start1 = 0;
        int end1 = start1 + gap - 1;
        int start2 = end1 + 1;
        int end2 = (start2 + gap - 1) < (array.length - 1) ? (start2 + gap - 1) : (array.length - 1);

        // 若有两个归并段(start2<length)就说明存在两个归并段
        while (start2 < array.length) {
            // 完成1次归并排序
            while (start1 <= end1 && start2 <= end2) {
                if (array[start1] <= array[start2]) {
                    tmpArray[tmpIndex++] = array[start1++];
                } else {
                    tmpArray[tmpIndex++] = array[start2++];
                }
            }
            // 下面两个while循环对应上面循环跳出的两种情况
            while (start1<=end1){
                tmpArray[tmpIndex++] = array[start1++];
            }

            while (start2<=end2){
                tmpArray[tmpIndex++] = array[start2++];
            }
            start1 = end2 + 1;
            end1 = start1 + gap - 1;
            start2 = end1 + 1;
            end2 = (start2 + gap - 1) < (array.length - 1) ? (start2 + gap - 1) : (array.length - 1);
        }

        // 退出循环可能是start2越界，但[start1,end1]还存在数据，此时已到数组的末尾
        // 然而此时的end1也有可能越界，如果gap为5，只剩下3个数据，end1=start+5-1=4，此时end1也处于越界位置
        // 因此此时只能使用array.length作为终止条件
        while (start1 < array.length) {
            tmpArray[tmpIndex++] = array[start1++];
        }

//        System.out.println(Arrays.toString(tmpArray));

        // 拷贝数据至原数组
        // 同样，只能使用遍历每一个值的方式而不能使用Java提供的那几种方式
        // 改变了形参array的引用，而指向了另一块与tmpArray元素完全相同的存储地址
        for (int i = 0; i < tmpArray.length; i++) {
            array[i] = tmpArray[i];
        }
    }


//    private static void mergeSort2(int[] array) {
//        for (int i = 1; i < array.length; i *= 2) {
//            merge2(array, i);
//        }
//    }
//
//    private static void merge2(int[] array, int gap) {
//        int[] tmpArray = new int[array.length];
//        int i = 0;
//        int start1 = 0;
//        int end1 = start1 + gap - 1;
//        int start2 = end1 + 1;
//        int end2 = start2 + gap - 1 <= array.length - 1 ? start2 + gap - 1 : array.length - 1;
//
//        // 保证有两个归并段
//        while (start2 < array.length) {
//            // 两个归并段都有数据
//            while (start1 <= end1 && start2 <= end2) {
//                // 比较
//                if (array[start1] <= array[start2]) {
//                    tmpArray[i++] = array[start1++];
//                } else {
//                    tmpArray[i++] = array[start2++];
//                }
//            }
//            while (start1 <= end1) {
//                tmpArray[i++] = array[start1++];
//            }
//            while (start2 <= end2) {
//                tmpArray[i++] = array[start2++];
//            }
//            // 该while的上面代码是证明一次二路归并已经完成
//            start1 = end2 + 1;
//            end1 = start1 + gap - 1;
//            start2 = end1 + 1;
//            end2 = start2 + gap - 1 <= array.length - 1 ? start2 + gap - 1 : array.length - 1;
//        }
//
//        // 只有一个归并段的情况
//        // 万一end1是不存在的，不能直接写start1<=end1
//        while (start1 < array.length) {
//            tmpArray[i++] = array[start1++];
//        }
//
//        // 拷贝数据到原始数组中
//        // 此处不能使用Array.copyOf()/xxx，只能通过for循环引用下标一个一个进行拷贝
//        for (int j = 0; j < tmpArray.length; j++) {
//            array[j] = tmpArray[j];
//        }
//    }
}
