package day09;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/14
 * Description: n的阶乘末尾0的个数
 *      将n的阶乘中的每个因数分解为质因数，1*2*3*(2*2)*5*(2*3)...
 *      只有当2*5时才会产生0，而2的个数明显远远大于5的个数
 *      因此最终结尾为0的个数==分解为质因数后5的个数
 *
 *      n/5的结果表示：当前n值中质因数中只出现1次5的数的个数
 *      有可能还存在质因数出现2次5的情况——能整除25
 *      质因数出现3次5的情况——能整除125
 *      质因数出现4次5的情况——能整除625
 *
 *      之所以使用n/5，意思就是当前n值中存在几个5的倍数(x*y*z*...*5)
 *      再/5，说明当前n值中存在几个25的倍数（x*y*z*...*5*5）,由于上一个5在上一次循环算过了，此次再加1个5即可
 *      再/5，说明当前n值中存在几个125的倍数(x*y*z*...5*5*5)，由于上两个5在上一次循环算过了，此次再加1个5即可
 *      再/5，说明当前n值中存在几个625的倍数(x*y*z*...5*5*5*5)，由于上三个5在上一次循环算过了，此次再加1个5即可
 */
public class ZeroCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

//        int count = 0;
//        while (n>0){
//            n = n/5;
//            count += n;
//        }
//        System.out.println(count);

        // 只讲真实，整除5->1个0；整除25->2个0；整除125->3个0；整除625->4个0
        // 但需要注意，能整除625的肯定能整除125,25,5，因此需要调整顺序，排除重复的情况
        int count = 0;
        for (int i = 1; i <= n; i++) {
            // 能被5整除，count+1，多加1个0
            if(i%5==0){
                count++;
            }
            // 要是还能被25整除，count再加1，多加2个0
            if(i%25==0){
                count++;
            }
            // 还能被125整除，count再加1，多加3个0
            if(i%125==0){
                count++;
            }
            // 还能被625整除，count再加1，多加4个0
            if(i%625==0){
                count++;
            }
        }
        System.out.println(count);
    }
}
