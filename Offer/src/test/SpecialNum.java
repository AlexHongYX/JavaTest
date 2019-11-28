package test;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/28
 * Description:
 */
public class SpecialNum {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int n = scanner.nextInt();
            if (n==0){
                break;
            }
            int val = 2;
            int index = 1;
            int[] nums = new int[n];
            nums[0] = 1;
            while (index<nums.length){
                if (isSpecial(val)){
                    nums[index++] = val;
                }
                val++;
            }

            System.out.println(nums[n-1]);
        }
    }

    public static boolean isSpecial(int num){
        if (num == 1){
            return true;
        }else if (num == 0){
            return false;
        }
        if (num%2==0){
            return isSpecial(num/2);
        }else if(num%3==0){
            return isSpecial(num/3);
        }else if(num%7==0){
            return isSpecial(num/7);
        }
        return false;
    }
}
