package com.xiaoaxiao.heap_test.kth_largest;

/**
 * Created by xiaoaxiao on 2019/7/31
 * Description:
 */
public class Main {

    public static void main(String[] args) {
        int k = 3;
        int[] arr = {4,5,8,2};
        KthLargest kthLargest = new KthLargest(3, arr);
        System.out.println(kthLargest.add(3));     // returns 4
        kthLargest.show();

        System.out.println(kthLargest.add(5));      // returns 5
        kthLargest.show();

        System.out.println(kthLargest.add(10));     // returns 5
        kthLargest.show();

        System.out.println(kthLargest.add(9));      // returns 8
        kthLargest.show();

        System.out.println(kthLargest.add(4));      // returns 8
        kthLargest.show();

    }
}
