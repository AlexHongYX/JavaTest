package day05;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/11
 * Description: 神奇的口袋：一组数的和为40
 */
public class Sum40 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int[] arr = new int[len];
        int i = 0;

        while(len!=0){
            arr[i++] = scanner.nextInt();
            len--;
        }

        System.out.println(count(arr,0,40));

    }

    private static int count(int[] arr, int i,int residue) {
        if(residue==0){
            return 1;
        }
        if(i==arr.length||residue<0){
            return 0;
        }
        return count(arr,i+1,residue) + count(arr,i+1,residue-arr[i]);
    }
}
