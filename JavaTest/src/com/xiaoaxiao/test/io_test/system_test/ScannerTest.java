package com.xiaoaxiao.test.io_test.system_test;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/7/26
 * Description: Scanner基本语法
 */
public class ScannerTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入一个整数");

        if(scanner.hasNextInt()){
            System.out.println(scanner.nextInt());
        }

        scanner.close();
    }
}
