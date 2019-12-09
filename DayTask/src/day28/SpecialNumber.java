package day28;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/12/9
 * Description: 守形数
 */
public class SpecialNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int n = scanner.nextInt();
            if (isNumber(n)){
                System.out.println("Yes!");
            }else {
                System.out.println("No!");
            }
        }
    }

    private static boolean isNumber(int n) {
        int nPow = (int)Math.pow(n,2);
        int i = 1;
        while (nPow%Math.pow(10,i)!=nPow){
            if (nPow%Math.pow(10,i)==n){
                return true;
            }
            i++;
        }
        return false;
    }
}
