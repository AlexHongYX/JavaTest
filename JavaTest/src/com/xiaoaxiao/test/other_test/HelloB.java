package com.xiaoaxiao.test.other_test;

/**
 * Created by xiaoaxiao on 2019/8/25
 * Description:
 */
class HelloA {
    //构造⽅法
    public HelloA(){
        System.out.println("Hello A!⽗类构造⽅法");
    }
    //⾮静态代码块
    {
        System.out.println("i'm A class.⽗类⾮静态代码块");
    }
    //静态代码块
    static{
        System.out.println("static A ⽗类静态代码块");
    }
}
public class HelloB extends HelloA {

    public int a = 0;
    //构造⽅法
    public HelloB() {
        super();
        this.a = 5;
        System.out.println("Hello B! 构造⽅法");
    }

    //⾮静态代码块
    {
        System.out.println("i'm B class.⾮静态代码块");
    }

    //静态代码块
    static {
        System.out.println("static B 静态代码块");
    }

    public static void main(String[] args) {
        System.out.println("---start---");
        new HelloB();
        new HelloB();
        System.out.println("---end---");
    }
}
