package day25;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/12/5
 * Description: 学分绩点
 */
public class GradeSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        // 学分数组
        int[] credits = new int[n];
        // 实际分数数组
        int[] scores = new int[n];

        for (int i = 0; i < n; i++) {
            credits[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            scores[i] = scanner.nextInt();
        }

        double GPA = 0.0;
        double Sum = 0.0;
        for (int i = 0; i < n; i++) {
            Sum += credits[i]*getJi(scores[i]);
        }

        int val = 0;
        for (int i = 0; i < n; i++) {
            val += credits[i];
        }

        GPA = Sum/val;
        System.out.printf("%.2f",GPA);

    }

    private static double getJi(int score) {
       if (score>=90){
           return 4.0;
       }else if (score>=85){
           return 3.7;
       }else if (score>=82){
           return 3.3;
       }else if (score>=78){
           return 3.0;
       }else if (score>=75){
           return 2.7;
       }else if (score>=72){
           return 2.3;
       }else if (score>=68){
           return 2.0;
       }else if (score>=64){
           return 1.5;
       }else if (score>=60){
           return 1.0;
       }else {
           return 0;
       }
    }
}
