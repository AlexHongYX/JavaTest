package com.xiaoaxiao.test.collection_test;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by xiaoaxiao on 2019/7/27
 * Description: Set相关方法的测试
 */
public class SetTest {

    public static void main(String[] args) {
        Set<String> set = new TreeSet<>();

        set.add("Hello");
        set.add("world");
        set.add("hello");
//        set.add("Hello");
        System.out.println(set);
    }
}
