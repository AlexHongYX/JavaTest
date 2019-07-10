package com.xiaoaxiao.test.GenericTest;

/**
 * Created by xiaoaxiao on 2019/7/10
 * Description:泛型类与泛型方法结合使用
 */

class Point3<T>{
    private T x;
    private T y;

    public T testMethod1(T t){
        return t;
    }

    public <E> E testMethod2(E e){
        return e;
    }
}
public class GenericMethodTest {

    public static void main(String[] args) {
        Point3<String> point3 = new Point3<>();
//        point3.testMethod1(123);      会报错（该方法的T和泛型类对应的相同）
        System.out.println(point3.testMethod1("Hello"));
        System.out.println(point3.testMethod2(123));    //该泛型方法和泛型类无关
    }
}
