package com.xiaoaxiao.test.inherit_test.multipleinherit;

/**
 * Created by xiaoaxiao on 2019/9/2
 * Description: 通过内部类实现"多继承"
 */

class D{
    public void getD(){
        System.out.println("This is D");
    }
}

class E{
    public void getE(){
        System.out.println("This is E");
    }
}

class F{
    class F1 extends D{
        public void getDAndF1(){
            System.out.println("This is F1");
            getD();
        }
    }
    class F2 extends E{
        public void getEAndF2(){
            System.out.println("This is F2");
            getE();
        }
    }
    public void fun(){
        // 在外部类中的方法中，调用内部类的方法与属性(内部类对外部类外部并不可见)
        // 内部类只在外部类中被调用，对外部类的外部不可见，这种设计更能体现封装性
        new F1().getDAndF1();
        new F2().getEAndF2();
    }
}

public class MultipleInherit2 {

    public static void main(String[] args) {
        F f = new F();
        f.fun();
    }
}
