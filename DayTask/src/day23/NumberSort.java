package day23;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/12/3
 * Description: 数字分类
 */
public class NumberSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] str = new int[n];
        for (int i = 0; i < str.length; i++) {
            str[i] = scanner.nextInt();
        }

        int A1 = 0;
        boolean isA1Use = false;
        int A2 = 0;
        boolean isA2Use = false;
        int A2flag = 1;
        int A3 = 0;
        boolean isA3Use = false;
        int A4 = 0;
        boolean isA4Use = false;
        int A4Count = 0;
        int A5 = 0;
        boolean isA5Use = false;

        for (int i = 0; i < str.length; i++) {
            int val = str[i]%5;
            if (val==0&&str[i]%2==0){
                A1 += str[i];
                isA1Use = true;
            }else if (val==1){
                A2 += str[i]*A2flag;
                A2flag *= -1;
                isA2Use = true;
            }else if (val==2){
                A3++;
                isA3Use = true;
            }else if(val==3){
                A4 += str[i];
                A4Count++;
                isA4Use = true;
            }else if (val==4){
                if (str[i]>A5){
                    A5 = str[i];
                }
                isA5Use = true;
            }
        }

        if (isA1Use){
            System.out.print(A1+" ");
        }else {
            System.out.print("N ");
        }

        if (isA2Use){
            System.out.print(A2+" ");
        }else {
            System.out.print("N ");
        }

        if (isA3Use){
            System.out.print(A3+" ");
        }else {
            System.out.print("N ");
        }

        if (isA4Use){
            System.out.printf("%.1f ",A4*1.0f/A4Count);
        }else {
            System.out.print("N ");
        }

        if (isA5Use){
            System.out.println(A5);
        }else {
            System.out.println("N");
        }

    }
}
