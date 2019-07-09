package com.xiaoaxiao.test;

/**
 * Created by xiaoaxiao on 2019/7/8
 * Description: 字符串的首字母大写=字符串截取+字符串大写
 */
public class StringTest {

    public static void main(String[] args){
//        System.out.println(firstCase("hello"));
        String str = "hello";
        str += "world";
        System.out.println(str);

    }

    private static String firstCase(String str){
        return str.substring(0,1).toUpperCase()+str.substring(1);
    }
}
