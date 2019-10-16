package com.xiaoaxiao.test.other_test;

/**
 * Created by xiaoaxiao on 2019/10/15
 * Description:
 */
import java.util.Scanner;

public class Reverse {
    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * 输入: 123
     * 输出: 321
     * <p>
     * 输入: -123
     * 输出: -321
     * <p>
     * 输入: 120
     * 输出: 21
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        if (x > Integer.MAX_VALUE) {
            return 0;
        }
        String s = String.valueOf(x);
        char[] chars = s.toCharArray();
        String newString = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            newString += chars[i];
        }
        //System.out.println("转换成字符数组后："+Arrays.toString(chars));
        int result = Integer.valueOf(newString);

        return result;
    }

    public static void main(String[] args) {
        Reverse r = new Reverse();
        Scanner scanner = new Scanner(System.in);
        System.out.println("转换后：" + r.reverse(scanner.nextInt()));
    }
}

