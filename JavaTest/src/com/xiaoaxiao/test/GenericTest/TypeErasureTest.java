package com.xiaoaxiao.test.GenericTest;

/**
 * Created by xiaoaxiao on 2019/7/10
 * Description: 类型擦除测试
 */

class Point <T>{
    private T x;
}

public class TypeErasureTest {
    public static void main(String[] args) {
        Point<String> point1 = new Point<>();
        Point<Integer> point2 = new Point<>();
        // point1和point2的class相同说明：泛型被类型擦除了，实际进入JVM的都是普通类
        System.out.println(point1.getClass()==point2.getClass());
    }
}
