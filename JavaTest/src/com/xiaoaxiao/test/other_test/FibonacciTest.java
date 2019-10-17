package com.xiaoaxiao.test.other_test;

import com.sun.xml.internal.messaging.saaj.util.FinalArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by xiaoaxiao on 2019/10/12
 * Description:
 */
public class FibonacciTest {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int i = 0;
        while (Fibonacci(i)<n){
            i++;
        }

        int result = Math.min(n-Fibonacci(i-1),Fibonacci(i)-n);
        System.out.println(result);
    }

    public static int Fibonacci(int x){
        if(x==0){
            return 0;
        }
        if(x==1){
            return 1;
        }
        return Fibonacci(x-1)+Fibonacci(x-2);
    }
}
