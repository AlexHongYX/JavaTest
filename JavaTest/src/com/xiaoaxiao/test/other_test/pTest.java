package com.xiaoaxiao.test.other_test;

/**
 * Created by xiaoaxiao on 2019/9/21
 * Description: 对象多态性测试
 */
class Person{
    public void fun(){
        System.out.println("我是学生");
    }
    public void show(){
        System.out.println("你好人类");
    }
}

class Student extends Person{

    public Student() {

    }

    public void fun(){
        System.out.println("我是学生");
        super.show();

    }
    public void show(){
        System.out.println("你好学生");
    }
}
public class pTest{
    public static void main(String[] args){
        test(new Student());
//        test(new Worker());
    }

    public static void test(Person per){
        per.fun();
        per.show();
    }
}

//class Worker extends Person{
//    public void fun(){
//        System.out.println("我是工人");
//    }
//    public void show(){
//        System.out.println("你好工人");
//    }
//}


