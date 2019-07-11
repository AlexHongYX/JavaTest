package com.xiaoaxiao.test.methodReferenceTest;

/**
 * Created by xiaoaxiao on 2019/7/11
 * Description: 方法引用——引用静态方法
 *              引用String类的valueOf方法
 *              自定义接口的抽象方法必须和引用的方法格式相同
 */
@FunctionalInterface
interface IUtil1<P,R>{

    R switchPara(P p);
}

public class MethodTest1 {

    public static void main(String[] args) {
        IUtil1<Integer,String> iUtil = String::valueOf; //进行方法引用
        String ret = iUtil.switchPara(123); //相当于调用了String.valueOf(123);
        System.out.println(ret.length());
    }
}
