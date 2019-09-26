package com.xiaoaxiao.test.generic_test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/9/26
 * Description: 上/下通配符详解
 */


class Eat{
    public void description(){
        System.out.println("this is Eat");
    }
}

class Fruit extends Eat{
    public void description(){
        System.out.println("this is Fruit");
    }
}

class Apple extends Fruit{
    public void description(){
        System.out.println("this is Apple");
    }
}

class Banana extends Fruit{
    public void description(){
        System.out.println("this is Banana");
    }
}

class Person<T>{
    private T first;

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }
}

public class Wildcard {

    public static void main(String[] args) {
        Person<? super Fruit> person = new Person<>();
        person.setFirst(new Fruit());
        person.getFirst();
    }

    public static void addMethod1(List<? extends Fruit> list){
//        list.add(new Apple());
        Fruit fruit = list.get(0);
    }

    public static void addMethod2(List<? super Fruit> list){
        list.add(new Apple());
//        list.add(new Eat());
        Object object = list.get(0);
    }
}
