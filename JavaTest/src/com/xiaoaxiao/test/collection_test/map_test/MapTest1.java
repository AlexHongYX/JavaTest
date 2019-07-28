package com.xiaoaxiao.test.collection_test.map_test;

import java.util.*;

/**
 * Created by xiaoaxiao on 2019/7/28
 * Description: Map的简单使用
 */
public class MapTest1 {

    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<>();

        map.put(1,"hello");
        map.put(2,"world");
        map.put(1,"Hello");
        map.put(3,"Java");

        Set<Integer> keySet = map.keySet();
        Iterator<Integer> integerIterator = keySet.iterator();
        while (integerIterator.hasNext()){
            System.out.println(integerIterator.next());
        }

        System.out.println("==========================");
        Collection<String> valueCollection = map.values();
        Iterator<String> stringIterator = valueCollection.iterator();
        while (stringIterator.hasNext()){
            System.out.println(stringIterator.next());
        }
    }
}
