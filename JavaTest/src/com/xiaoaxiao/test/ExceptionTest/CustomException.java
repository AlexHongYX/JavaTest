package com.xiaoaxiao.test.exceptionTest;

/**
 * Created by xiaoaxiao on 2019/7/9
 * Description: 自定义异常测试
 */
class AddException extends RuntimeException {
    public AddException(String msg) {
        super(msg);
    }
}

public class CustomException {
    public static void main(String[] args) {
        int a = 1;
        int b = 1;
        System.out.println("Hello");

        int c = a + b;
        if (2 == c) {
            throw new AddException("不能为2");
        }
    }
}