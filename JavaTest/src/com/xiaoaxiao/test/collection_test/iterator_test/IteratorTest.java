package com.xiaoaxiao.test.collection_test.iterator_test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/7/28
 * Description: Iterator迭代器的使用
 */
public class IteratorTest {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();

        stringList.add("Hello");
        stringList.add("World");
        stringList.add("Messi");

        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
