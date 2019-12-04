package day24;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/12/4
 * Description: 最大公约数变种
 */
public class FinalValue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int n = scanner.nextInt();
            int a = scanner.nextInt();

            int[] b = new int[n];

            for (int i = 0; i < b.length; i++) {
                b[i] = scanner.nextInt();
            }

            for (int i = 0; i < b.length; i++) {
                if (b[i]<=a){
                    a+=b[i];
                }else {
                    a += getMax(b[i],a);
                }
            }

            System.out.println(a);
        }
    }

    private static int getMax(int a,int b){
        if(a<b){
            int tmp = a;
            a = b;
            b = tmp;
        }
        int c = a%b;
        while (c!=0){
            a = b;
            b = c;
            c = a%b;
        }
        return b;
    }
}
