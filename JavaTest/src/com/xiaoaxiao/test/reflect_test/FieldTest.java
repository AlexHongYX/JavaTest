package com.xiaoaxiao.test.reflect_test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by xiaoaxiao on 2019/7/20
 * Description: 反射与普通属性
 */

class Test{
    public int age;
}

public class FieldTest {

    public static void main(String[] args) throws Exception{
        // 1、拿到Person类的Class对象
        Class<?> cls = Test.class;
        // 2、利用反射创建Person的实例对象
        Test person = (Test) cls.newInstance();
        // 3、拿到age的Field（反射）对象
        Field field = cls.getField("age");
        // 4、调用Filed中相关方法
        field.set(person,10);
        System.out.println(field.get(person));
    }
}
