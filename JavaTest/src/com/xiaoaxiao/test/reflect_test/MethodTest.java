package com.xiaoaxiao.test.reflect_test;

import java.lang.reflect.Method;

/**
 * Created by xiaoaxiao on 2019/7/20
 * Description: 反射调用类中普通方法测试
 */

class Animal{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class MethodTest {

    public static void main(String[] args) throws Exception{
        // 1、拿到Animal类的Class对象
        Class<?> cls = Animal.class;
        // 2、利用反射创建Animal的实例对象
        Animal animal = (Animal) cls.newInstance();
        // 3、拿到setName的Method（反射）对象
        Method setNameMethod = cls.getMethod("setName", String.class);
        // 4、通过invoke调用方法
        setNameMethod.invoke(animal,"hehe");
        System.out.println(animal);
    }
}
