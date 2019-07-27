package com.xiaoaxiao.test.reflect_test;

import java.lang.reflect.Field;

/**
 * Created by xiaoaxiao on 2019/7/20
 * Description: 动态破坏封装测试
 */

class MyTest{
    private int age;
}

public class AccessibleTest {

    public static void main(String[] args) throws Exception{
        // 正向调用 不能调用private属性
//        MyTest myTest = new MyTest();
//        myTest.age;
        Class<?> cls = MyTest.class;

        MyTest myTest = (MyTest)cls.newInstance();

        Field field = cls.getDeclaredField("age");

        // 动态破坏封装
        field.setAccessible(true);

        // 动态破坏封装只对反射有效，对正向没用
//        myTest.age;

        field.set(myTest,100);
        System.out.println(field.get(myTest));
    }
}
