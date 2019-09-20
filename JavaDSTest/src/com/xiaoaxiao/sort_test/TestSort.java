package com.xiaoaxiao.sort_test;

import java.util.Arrays;

/**
 * Created by xiaoaxiao on 2019/9/19
 * Description:
 */
public class TestSort {

    public static void main(String[] args) {
//        int[] array = {10, 3, 2, 78, 45, 11, 33};
        int[] array = {6,1,2,7,9,3,4,5,10,8};
        System.out.println(Arrays.toString(array));
//        insertSort(array);
//        shellSort(array);
//        selectSort(array);
//        bubbleSort(array);
//        System.out.println(partion(array,0,array.length-1));\
        quickSort(array);
        System.out.println(Arrays.toString(array));
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
     * @param array 形参数组
     */
    private static void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j]<array[i]){
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
     * @param array 形参数组
     */
    private static void bubbleSort(int[] array){
        for (int i=0;i<array.length-1;i++){   // i代表的是趟数(5个数比较4次即可)
            boolean flag = true;
            for (int j=0;j<array.length-i-1;j++){
                if (array[j]>array[j+1]){
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    flag = false;
                }
            }
            // 如果第i趟没有进行交换，证明已经有序，之后就不用再执行了。直接break
            if (flag){
                break;
            }
        }
    }

    /**
     * 快速排序
     * 时间复杂度：O(NlogN)
     * 空间复杂度：O(logN)
     * 稳定性：不稳定(有跳跃式的交换)
     *
     * @param array 形参数组
     */
    private static void quickSort(int[] array){
        quick(array,0,array.length-1);
    }

    private static void quick(int[] array,int start,int end){
        // 先三数取中
        medianOfThree(array,start,end);
        int par = partion(array,start,end);
        // 递归左边
        // 保证左边的元素个数>=2，若是1个就已经有序了
        if (par>start+1){
            quick(array,start,par-1);
        }
        if (par<end-1){
            quick(array,par+1,end);
        }
    }

    /**
     * 一趟快速排序
     * @param array 形参数组
     * @param low   开始下标位置
     * @param high  结束下标位置
     * @return  返回一次快排后的基准位置
     */
    private static int partion(int[] array,int low,int high){
        int tmp = array[low];
        // 一轮做两个事，先让high走后往前走，再让low从前往后走
        while (low<high){
            // -的过程需要控制范围low<high
            while (low<high&&array[high]>=tmp){
                high--;
            }
            // 由于从上面的while循环退出有两种可能
            //      1、low<high但array[high]<tmp，将high对应的值赋给low对应的值
            //      2、low>=high，直接退出即可(本次快排结束)
            // 所以要分别讨论两种可能
            if (low<high){
                array[low] = array[high];
            }else {
                break;
            }

            while (low<high&&array[low]<=tmp){
                low++;
            }
            if (low<high){
                array[high] = array[low];
            }else {
                break;
            }
        }
        array[low] = tmp;
        return low;
    }

    /**
     * 三数取中
     *      array[mid] <= array[low] <= array[high]
     * @param array 形参数组
     * @param low   low下标
     * @param high  high下标
     */
    private static void medianOfThree(int[] array,int low,int high){
        int mid = (low+high)/2;
        if (array[mid]>array[high]){
            int tmp = array[mid];
            array[mid] = array[high];
            array[high] = tmp;
        }
        if(array[low]>array[high]){
            int tmp = array[low];
            array[low] = array[high];
            array[high] = tmp;
        }

        if (array[low]<array[mid]){
            int tmp = array[low];
            array[low] = array[mid];
            array[mid] = tmp;
        }
    }
}
