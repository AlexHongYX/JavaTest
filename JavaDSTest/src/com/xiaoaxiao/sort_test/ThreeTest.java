package com.xiaoaxiao.sort_test;

/**
 * Created by xiaoaxiao on 2019/9/20
 * Description:
 */
public class ThreeTest {

    public static void main(String[] args) {
        int[] array = {4,6,3};
        medianOfThree(array,0,2);
        for (int i:array){
            System.out.println(i);
        }
    }

    //  array[mid] <= array[low] <= array[high];
    public static void medianOfThree(int[] array,
                                     int low,int high) {
        int mid = (low+high)/2;
        if(array[mid] > array[low]) {
            swap(array,mid,low);
        }//array[mid] <= array[low]

//        if(array[mid] > array[high]){
//            swap(array,mid,high);
//        }//array[mid] <= array[high]

        if(array[low] > array[high]) {
            swap(array,low,high);
        }//array[low] <= array[high]

        //array[mid] <= array[low] <= array[high];
    }

    public static void swap(int[] array,int low,int high) {
        int tmp = array[low];
        array[low] = array[high];
        array[high] = tmp;
    }
}
