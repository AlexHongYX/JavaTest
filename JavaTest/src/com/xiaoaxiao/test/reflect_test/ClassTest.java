package com.xiaoaxiao.test.reflect_test;

import java.util.Date;

/**
 * Created by xiaoaxiao on 2019/7/20
 * Description: Class类的相关测试
 */

class Person{}
interface IMsg1{}
interface IMsg2{}

class Student extends Person implements IMsg1,IMsg2{}

public class ClassTest {

    public static void main(String[] args) throws Exception {
        // Date date = new Date();
        // 获得Class对象
        Class<Date> cls = (Class<Date>) Class.forName("java.util.Date");
        // 通过反射取得Date类实例化
        Date date = cls.newInstance();
        System.out.println(date);

        Class<?> cls1 = Class.forName("com.xiaoaxiao.test.reflect_test.Student");
        System.out.println(cls1.getSuperclass().getName());

        Class<?>[] classes = cls1.getInterfaces();
        for (Class classs:classes){
            System.out.println(classs.getName());
        }
    }
}
