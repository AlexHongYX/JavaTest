package com.xiaoaxiao.test.methodReferenceTest;

/**
 * Created by xiaoaxiao on 2019/7/11
 * Description: 方法引用——引用某个对象的方法
 *              引用String类的toUpperCase方法
 */

interface IUtil2<R>{
    R switchPara();
}

public class MethodTest2 {

    public static void main(String[] args) {
        IUtil2<String> iUtil2 = "hello"::toUpperCase;
        String ret = iUtil2.switchPara();
        System.out.println(ret);
    }
}
