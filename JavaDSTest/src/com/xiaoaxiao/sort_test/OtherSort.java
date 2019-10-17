package com.xiaoaxiao.sort_test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/9/23
 * Description: 非比较排序——知道思想即可，没必要写代码
 *      桶排序，基数排序，计数排序
 */
public class OtherSort {
    static final int BUCKETS = 256;

    public static void radixSortA(String[] arr,int stringLen){

        // 泛型数组不能直接定义，需要将Object数组强转为对应的泛型数组
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
