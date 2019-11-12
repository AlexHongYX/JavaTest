package day07;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/12
 * Description: 最小公倍数
 */
public class MinMultiple {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        if(m<n){
            int tmp = m;
            m = n;
            n = tmp;
        }

        System.out.println(m%n==0?m:(m*n)/findMaxYue(m,n));
    }

    public static int findMaxYue(int m,int n){
        int i = 2;
        int ret = 1;
        while (i<=n&&i<=m){
            if(m%i==0&&n%i==0){
                ret = i;
            }
            i++;
        }
        return ret;
    }
}
