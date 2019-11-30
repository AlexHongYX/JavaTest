package com.xiaoaxiao.test.other_test;

/**
 * Created by xiaoaxiao on 2019/10/28
 * Description:
 */

public class SendValue{
    public String str="6";

    public static void main(String[] args) {
        SendValue sv=new SendValue();
        sv.change(sv.str);
        System.out.println(sv.str);
    }

    public void change(String str) {
        this.str="10";
    }
}

