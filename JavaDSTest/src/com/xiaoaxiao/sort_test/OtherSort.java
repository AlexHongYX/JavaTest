package com.xiaoaxiao.sort_test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/9/23
 * Description: 非比较排序
 *      桶排序，基数排序，计数排序
 */
public class OtherSort {
    static final int BUCKETS = 256;

    public static void radixSortA(String[] arr,int stringLen){

        @SuppressWarnings("checked")
        ArrayList<String>[] buckets = (ArrayList<String>[])new Object[BUCKETS];


        for (int i = 0; i < BUCKETS; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int pos = stringLen-1;pos>=0;pos--){
            for (String s:arr){
                buckets[s.charAt(pos)].add(s);
            }

            int idx = 0;
            for (ArrayList<String> thisBucket:buckets){
                for (String s:thisBucket){
                    arr[idx++] = s;
                }
                thisBucket.clear();
            }
        }
    }
}
