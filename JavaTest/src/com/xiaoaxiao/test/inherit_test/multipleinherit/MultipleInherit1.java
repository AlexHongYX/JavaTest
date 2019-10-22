package com.xiaoaxiao.test.inherit_test.multipleinherit;

/**
 * Created by xiaoaxiao on 2019/9/2
 * Description: 通过多层继承实现"多继承"
 */

class A{
    public void getA(){
        System.out.println("This is A");
    }
}

class B extends A{
    public void getB(){
        System.out.println("This is B");
    }
}

class C extends B{
    public void getC(){
        System.out.println("This is C");
    }
}

public class MultipleInherit1 {
    public static void main(String[] args) {
        // 此时的C通过继承B，也继承了A(B继承着A)，从而实现了"多继承"(C继承了A与B)
        C c = new C();
        c.getA();
        c.getB();
        c.getC();
    }
}
