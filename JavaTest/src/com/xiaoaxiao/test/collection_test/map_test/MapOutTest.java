package com.xiaoaxiao.test.collection_test.map_test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by xiaoaxiao on 2019/7/28
 * Description: 利用迭代器输出Map集合
 *              由于Map集合本身没有迭代器
 *                  1、先将Map集合转为Set集合
 *                  2、利用Set集合的迭代器输出
 */
public class MapOutTest {

    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"hello");
        map.put(2,"world");
        map.put(4,"Python");
        map.put(3,"Java");
        Set<Map.Entry<Integer,String>> set = map.entrySet();
        Iterator<Map.Entry<Integer,String>> iterator = set.iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer,String> entry = iterator.next();
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
