package com.xiaoaxiao.sort_test;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by xiaoaxiao on 2019/9/22
 * Description: 排序的代码反复书写，快排递归+非递归，归并递归+非递归，闲来无事就写一遍
 * 七大排序：
 * 直接插入，希尔(shell)排序，直接选择，冒泡排序，堆排序，快速排序，归并排序
 */
public class SortPractice {
    public static void main(String[] args) {
        int[] array = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
//        insertSort(array);
//        shellSort(array);
//        selectSort(array);
        bubbleSort(array);
//        quickSortRec(array);
//        quickSortNonRec(array);
//        mergeSortRec(array);
//        mergeSortNonRec(array);
        System.out.println(Arrays.toString(array));
    }


    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            boolean flag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    flag = false;
                }
            }

            if (flag) {
                break;
            }
        }
    }

    private static void quickSortRec(int[] array) {
        quickRec(array, 0, array.length - 1);
    }

    private static void quickRec(int[] array, int start, int end) {
        if (end - start + 1 < 16) {
            insertSort2(array, start, end);
        } else {

            medianOfThree(array,start,end);
            int par = partion(array, start, end);
            if (par > start + 1) {
                quickRec(array, start, par - 1);
            }
            if (par < end - 1) {
                quickRec(array, par + 1, end);
            }
        }
    }

    private static void insertSort2(int[] array, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int j = i-1;
            int tmp = array[i];
            while (j>=0&&array[j]>tmp){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = tmp;
        }
    }

    private static void medianOfThree(int[] array, int start, int end) {
        int mid = (start+end)/2;
        if(array[start]>array[end]){
            int tmp = array[start];
            array[start] = array[end];
            array[end] = tmp;
        }
        if (array[mid]>array[end]){
            int tmp = array[mid];
            array[mid] = array[end];
            array[end] = tmp;
        }
        if (array[mid]>array[start]){
            int tmp = array[mid];
            array[mid] = array[start];
            array[start] = tmp;
        }
    }

    private static int partion(int[] array, int start, int end) {
        int tmp = array[start];
        while (start < end) {
            while (start < end && array[end] >= tmp) {
                end--;
            }
            if (start < end) {
                array[start] = array[end];
            } else {
                break;
            }
            while (start < end && array[start] < tmp) {
                start++;
            }
            if (start < end) {
                array[end] = array[start];
            }
        }
        array[start] = tmp;
        return start;
    }

    private static void quickSortNonRec(int[] array) {

    }

    public static void insertSort(int[] array) {

    }

    public static void shellSort(int[] array) {

    }

    private static void shell(int[] array, int gap) {

    }

    private static void selectSort(int[] array) {

    }


    private static void mergeSortRec(int[] array) {

    }

    private static void mergeRec(int[] array, int start, int end, int[] tmpArray) {

    }

    private static void merge(int[] array, int start, int mid, int end, int[] tmpArray) {

    }

    private static void mergeSortNonRec(int[] array) {

    }

    private static void mergeNonRec(int[] array, int gap) {

    }
}
