package com.xiaoaxiao.test.other_test;

/**
 * Created by xiaoaxiao on 2019/9/22
 * Description: 代码块测试
 */

class Animal{
    public Animal(){
        System.out.println("this is Animal constructor");
    }
    {
        System.out.println("This is Animal construct block");
    }
    static {
        System.out.println("This is Animal static block");
    }
}

class Dog extends Animal{
    public Dog(){
        System.out.println("this is Dog constructor");
    }
    {
        System.out.println("This is Dog construct block");
    }
    static {
        System.out.println("This is Dog static block");
    }
}

public class CodeBlock {
    public static void main(String[] args) {
        Animal animal = new Dog();

    }
    //    public CodeBlock(){
//        System.out.println("this is constructor");
//    }
//    {
//        System.out.println("This is construct block");
//    }
//    static {
//        System.out.println("This is static block");
//    }
}


