package com.xiaoaxiao.test.inherit_test;

/**
 * Created by xiaoaxiao on 2019/8/10
 * Description: 继承中子类构造器与父类构造器的关系
 */

class Person{
    public Person(String name){
        System.out.println("person");
    }
}

class Student extends Person{
    public Student(String name){
        super("hello");

        System.out.println("student");
    }
}

public class InheritTest {

    public static void main(String[] args) {
        Student student = new Student("name");
    }
}
