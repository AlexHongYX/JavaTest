package day10;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/16
 * Description: 给一个数看移动几步可以成为Fibonacci数
 */
public class Fibonacci {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int highFib = 0;
        int lowFib = 0;
        int fib = 0;

        // 找到比当前值大的和比当前值小的fibonacci数
        while(n>highFib){
            lowFib = highFib;
            fib++;
            highFib = fibonacci(fib);
        }

        // 输出，当前值离较大值与较小值更近的差值
        System.out.println(Math.min((n-lowFib),(highFib-n)));


    }

    private static int fibonacci(int n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        return fibonacci(n-1)+fibonacci(n-2);
    }
}
