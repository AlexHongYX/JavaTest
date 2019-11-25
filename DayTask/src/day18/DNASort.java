package day18;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/25
 * Description: DNA序列
 */
public class DNASort {

    private static double rate;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();
        int n = scanner.nextInt();
        char[] chars = str.toCharArray();

        int index = 0;

        rate = getBig(chars,0,n);

        for (int i = 1; i < chars.length-n+1; i++) {
            if (getBig(chars,i,i+n)>rate){
                rate = getBig(chars,i,i+n);
                index = i;
            }
        }

        System.out.println(str.substring(index,index+n));
    }

    private static double getBig(char[] chars, int start, int end) {
        int count = 0;
        for (int i = start; i < end; i++) {
            if (chars[i]=='G'||chars[i]=='C'){
                count++;
            }
        }
        return count*1.0/(end-start);
    }
}
