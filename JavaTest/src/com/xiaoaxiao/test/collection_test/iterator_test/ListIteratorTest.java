package com.xiaoaxiao.test.collection_test.iterator_test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by xiaoaxiao on 2019/7/28
 * Description: ListIterator有List接口提供，Set不支持
 */
public class ListIteratorTest {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();

        stringList.add("Hello");
        stringList.add("World");
        stringList.add("Messi");

        ListIterator<String> listIterator = stringList.listIterator();

        while (listIterator.hasNext()){
            System.out.println(listIterator.next());
        }

        System.out.println("=========================");

        while (listIterator.hasPrevious()){
            System.out.println(listIterator.previous());
        }
    }
}
