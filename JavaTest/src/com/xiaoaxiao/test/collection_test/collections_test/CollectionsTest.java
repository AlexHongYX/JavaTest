package com.xiaoaxiao.test.collection_test.collections_test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/7/28
 * Description: Collections工具类相关方法测试
 */
public class CollectionsTest {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();

        Collections.addAll(stringList,"hello","world","happy","year");

        // 变为线程安全的集合（不推荐）
        List<String> safeList = Collections.synchronizedList(stringList);

        for (String str:stringList){
            System.out.println(str);
        }

        System.out.println("=================================");

        Collections.reverse(stringList);
        for (String str:stringList){
            System.out.println(str);
        }

        System.out.println("=================================");

        Collections.sort(stringList);
        for (String str:stringList){
            System.out.println(str);
        }
    }
}
