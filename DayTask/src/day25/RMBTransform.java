package day25;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/12/5
 * Description: 数字->人民币
 *      毫无意义的题目，代码堆+多次尝试=>结果需求
 */
public class RMBTransform {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double input = scanner.nextDouble();

        int zheng = (int) Math.floor(input);

//        System.out.println(zheng);
        StringBuilder sb = new StringBuilder();

        int val = zheng;
        int n = -1;
        while (val > 0) {
            n++;
            val /= 10;
        }

        while (zheng>0){
            int a = (int)(zheng/Math.pow(10,n));
            sb.append(transForm(a));
            if ((n+1)%9==0){
                sb.append("亿");
            }else if((n+1)%5==0){
                sb.append("万");
            }else if ((n+1)%4==3){
                sb.append("仟");
            }else if((n+1)%4==2){
                sb.append("佰");
            }else if((n+1)%4==1){
                sb.append("拾");
            }else if(n==1){
                sb.append("元");
            }
            zheng %= Math.pow(10,n);
            n--;
        }

        System.out.println(sb.toString());

    }

    private static char transForm(int a) {
        switch (a){
            case 1:return '壹';
            case 2:return '贰';
            case 3:return '叁';
            case 4:return '肆';
            case 5:return '伍';
            case 6:return '陆';
            case 7:return '柒';
            case 8:return '捌';
            case 9:return '玖';
        }
        return ' ';
    }
}
