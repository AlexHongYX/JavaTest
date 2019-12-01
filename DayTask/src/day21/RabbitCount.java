package day21;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/12/1
 * Description: 兔子三个月后生一只小兔子，小兔子三个月后再生一只小兔子，若兔子不死，n月时共有几只兔子
 *      兔子的繁殖符合斐波那契数列
 *          n月的兔子数 = n-1月的兔子数+n-2月的兔子数(n-2月的兔子中无论是n-2月已经是老兔子还是才成为新兔子)
 *          在n月时都会生出新兔子。一定要注意n-2的兔子分为两种。
 *
 *      进阶题：兔子只能活10个月
 *          n<10：符合斐波那契数列：f(n)=f(n-1)+f(n-2);
 *          n>=10：减去10个月前的兔子数即可：f(n)=f(n-1)+f(n-2)-f(n-10);
 */
public class RabbitCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(getTotalCount(n));
    }


    public static int getTotalCount(int monthCount){
        if (monthCount==1|monthCount==2){
            return 1;
        }
        return getTotalCount(monthCount-1)+getTotalCount(monthCount-2);
    }
}
