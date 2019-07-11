package com.xiaoaxiao.test.methodReferenceTest;

/**
 * Created by xiaoaxiao on 2019/7/11
 * Description: 方法引用——引用类中的普通方法
 *              引用String类的compareTo方法
 */

interface IUtil3<R,P>{
    R compare(P p1,P p2);
}

public class MethodTest3 {

    public static void main(String[] args) {
        IUtil3<Integer,String> iUtil3 = String::compareTo;
        int ret = iUtil3.compare("hello","world");  //相当于在调用"hello".compareTo("world");
        System.out.println(ret);
    }
}
