package com.xiaoaxiao.test.throw_test;

/**
 * Created by xiaoaxiao on 2019/11/14
 * Description: 异常测试——非RuntimeException必须在当前类被try-catch捕获
 */

class Hello{
    public void isException() throws Exception{
        throw new Exception("Hello Exception");
    }
}
public class ThrowTest {
    public static void main(String[] args) {
        Hello hello = new Hello();
        try {
            hello.isException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
