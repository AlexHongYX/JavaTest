package com.xiaoaxiao.sort_test;

import java.util.Arrays;

/**
 * Created by xiaoaxiao on 2019/9/19
 * Description:
 */
public class TestSort {

    public static void main(String[] args) {
        int[] array = {10,3,2,78,45,11,33};
        System.out.println(Arrays.toString(array));
//        insertSort(array);
        shellSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 直接插入排序
     *      时间复杂度：O(n²)
     *      空间复杂度：O(1)
     *      稳定性：稳定
     * @param array 形参数组
     */
    private static void insertSort(int[] array) {
        for (int i=1;i<array.length;i++){
            int j = i-1;
            int tmp = array[i];
            // 退出条件有两个
            //      1、找到第一个比array[i]小的元素(该元素前面的元素都小于array[i]——已排好序)
            //      2、j需要在数组下标范围内
            //      3、要先判断j>=0再判断array[j]>tmp，否则如果array[0]>array[i],j--了，此时应该先判断j>=0，
            //                              否则会出现数组下标越界异常(此时j==-1，应该依靠j>=0使其退出循环)
            // 由于此处array[j]>tmp，因此直接插入是稳定的，如果array[j]==tmp并不会进入循环
            while (j>=0&&array[j]>tmp){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = tmp;
        }
    }

    // shell排序：直接插入排序的优化

    /**
     * 希尔(shell)排序
     *      时间复杂度：O(n^1.3~n^2)
     *      空间复杂度：O(1)
     *      稳定性：不稳定(进行了跳跃式的交换)
     * @param array 形参数组
     */
    private static void shellSort(int[] array){
        int[] drr = {5,3,1};
        for (int i=0;i<drr.length;i++){
            shell(array,drr[i]);
        }
    }

    /**
     * shell排序的具体实现
     * @param array 形参数组
     * @param gap 跳跃间隔
     */
    private static void shell(int[] array, int gap) {
        for (int i = gap; i < array.length; i++) {
            int j = i-gap;
            int tmp = array[i];
            while (j>=0&&array[j]>tmp){
                array[j+gap] = array[j];
                j -= gap;
            }
            array[j+gap] = tmp;
        }
    }
}
