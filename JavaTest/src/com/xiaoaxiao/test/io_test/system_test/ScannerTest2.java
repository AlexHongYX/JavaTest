package com.xiaoaxiao.test.io_test.system_test;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/7/26
 * Description: Scanner与正则表达式搭配使用
 */
public class ScannerTest2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入生日：");

        if(scanner.hasNext("\\d{4}-\\d{2}-\\d{2}")){
            System.out.println(scanner.next());
        }else {
            System.out.println("生日格式不合法");
        }
        scanner.close();
    }
}
