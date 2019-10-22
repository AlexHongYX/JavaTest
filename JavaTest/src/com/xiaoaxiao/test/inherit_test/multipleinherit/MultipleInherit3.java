package com.xiaoaxiao.test.inherit_test.multipleinherit;

/**
 * Created by xiaoaxiao on 2019/9/2
 * Description: 使用接口实现"多继承"
 */

interface IA{
    void funA();
}

interface IB{
    void funB();
}

// 使用接口多继承实现"多继承"
interface IC extends IA,IB{
    void funC();
}

// 实现接口IC的同时需要实现接口IA，IB
class CImpl implements IC{

    @Override
    public void funA() {
        System.out.println("This is A");
    }

    @Override
    public void funB() {
        System.out.println("This is B");
    }

    @Override
    public void funC() {
        System.out.println("This is C");
    }
}

public class MultipleInherit3 {

    public static void main(String[] args) {
        IC c = new CImpl();
        c.funA();
        c.funB();
        c.funC();
    }
}
