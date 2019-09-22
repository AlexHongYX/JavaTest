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
//        bubbleSort(array);
//        quickSortRec(array);
//        quickSortNonRec(array);
//        mergeSortRec(array);
        mergeSortNonRec(array);
        System.out.println(Arrays.toString(array));
    }

    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            int j = i - 1;
            for (j = i - 1; j >= 0; j--) {
                if (array[j] > tmp) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = tmp;
        }
    }

    public static void shellSort(int[] array) {
        int[] gaps = {5, 3, 1};
        for (int i = 0; i < gaps.length; i++) {
            shell(array, gaps[i]);
        }
    }

    private static void shell(int[] array, int gap) {
        for (int i = gap; i < array.length; i++) {
            int tmp = array[i];
            int j = i - gap;
            while (j >= 0 && array[j] > tmp) {
                array[j + gap] = array[j];
                j -= gap;
            }
            array[j + gap] = tmp;
        }
    }

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

    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) { // i代表趟数，n个数组排n-1次即可
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
        if ((end - start + 1) < 16) {
            insertSort2(array, start, end);
        }
        medianOfThree(array, start, end);
        int par = partion(array, start, end);
        if (par > start + 1) {
            quickRec(array, start, par - 1);
        }
        if (par < end - 1) {
            quickRec(array, par + 1, end);
        }
    }

    private static void insertSort2(int[] array, int start, int end) {
        for (int i = start; i <= end; i++) {
            int tmp = array[i];
            int j = i - 1;
            while (j >= start && array[j] > array[i]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = tmp;
        }
    }

    private static void quickSortNonRec(int[] array) {
        Stack<Integer> stack = new Stack<>();
        int start = 0;
        int end = array.length - 1;
        int par = partion(array, start, end);
        if (par > start + 1) {
            stack.push(start);
            stack.push(par - 1);
        }
        if (par < end - 1) {
            stack.push(par + 1);
            stack.push(end);
        }
        while (!stack.empty()) {
            end = stack.pop();
            start = stack.pop();
            par = partion(array, start, end);
            if (par > start + 1) {
                stack.push(start);
                stack.push(par - 1);
            }
            if (par < end - 1) {
                stack.push(par + 1);
                stack.push(end);
            }
        }
    }

    private static void medianOfThree(int[] array, int start, int end) {
        int mid = (start + end) / 2;
        int tmp = 0;
        if (array[mid] > array[end]) {
            tmp = array[mid];
            array[mid] = array[end];
            array[end] = tmp;
        }
        if (array[start] > array[end]) {
            tmp = array[start];
            array[start] = array[end];
            array[end] = tmp;
        }
        if (array[mid] > array[start]) {
            tmp = array[start];
            array[start] = array[mid];
            array[mid] = tmp;
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
            while (start < end && array[start] <= tmp) {
                start++;
            }
            if (start < end) {
                array[end] = array[start];
            } else {
                break;
            }
        }
        array[start] = tmp;
        return start;
    }

    private static void mergeSortRec(int[] array) {
        int[] tmpArray = new int[array.length];
        mergeRec(array, 0, array.length - 1,tmpArray);
    }

    private static void mergeRec(int[] array, int start, int end,int[] tmpArray) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        mergeRec(array, start, mid,tmpArray);
        mergeRec(array,mid+1,end,tmpArray);
        merge(array,start,mid,end,tmpArray);
    }

    private static void merge(int[] array, int start, int mid, int end,int[] tmpArray) {
        int tmpIndex = start;
        int s2 = mid+1;
        int oldStart = start;
        while (start<=mid&&s2<=end){
            if (array[start]<=array[s2]){
                tmpArray[tmpIndex++] = array[start++];
            }else {
                tmpArray[tmpIndex++] = array[s2++];
            }
        }
        while (start<=mid){
            tmpArray[tmpIndex++] = array[start++];
        }
        while (s2<=end){
            tmpArray[tmpIndex++] = array[s2++];
        }

        for (int i=oldStart;i<=end;i++){
            array[i] = tmpArray[i];
        }
    }

    private static void mergeSortNonRec(int[] array) {
        for (int i = 1; i < array.length; i*=2) {
            mergeNonRec(array,i);
        }
    }

    private static void mergeNonRec(int[] array, int gap) {
        int[] tmpArray = new int[array.length];
        int tmpIndex = 0;
        int start1 = 0;
        int end1 = start1+gap-1;
        int start2 = end1+1;
        int end2 = start2+gap-1<=array.length-1?start2+gap-1:array.length-1;
        while (start2<array.length){
            while (start1<=end1&&start2<=end2){
                if (array[start1]<=array[start2]){
                    tmpArray[tmpIndex++] = array[start1++];
                }else {
                    tmpArray[tmpIndex++] = array[start2++];
                }
            }
            while (start1<=end1){
                tmpArray[tmpIndex++] = array[start1++];
            }
            while (start2<=end2){
                tmpArray[tmpIndex++] = array[start2++];
            }
            start1 = end2+1;
            end1 = start1+gap-1;
            start2 = end1+1;
            end2 = start2+gap-1<=array.length-1?start2+gap-1:array.length-1;
        }
        while (start1<array.length){
            tmpArray[tmpIndex++] = array[start1++];
        }
        for (int i = 0; i < tmpArray.length; i++) {
            array[i] = tmpArray[i];
        }
    }
}
