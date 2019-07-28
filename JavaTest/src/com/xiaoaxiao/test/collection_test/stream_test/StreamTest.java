package com.xiaoaxiao.test.collection_test.stream_test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by xiaoaxiao on 2019/7/28
 * Description: Stream类测试
 */
public class StreamTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        Collections.addAll(list, 1, 3, 5, 2, 6, 8, 7, 9);

        // 通过 方法引用 遍历输出集合中的每一个元素
        list.forEach(System.out::println);

        System.out.println("====================");
        // 获取Stream对象
        Stream<Integer> stream = list.stream();

//        // 统计出集合中所有偶数的个数
//        System.out.println(stream.filter(e -> e % 2 == 0).count());
//        System.out.println("====================");

        // 找到集合中的最大值
        System.out.println(stream.max(Integer::compareTo).get());
    }
}
