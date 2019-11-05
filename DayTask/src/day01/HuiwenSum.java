package day01;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by xiaoaxiao on 2019/11/5
 * Description: 回文统计
 */
public class HuiwenSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String A = scanner.next();
        String B = scanner.next();

        int count = 0;
        int length = A.length();

        for (int i = 0; i < length; i++) {
            if (isHuiwen(A.substring(0,i)+B+A.substring(i,length))) {
                count++;
            }
        }

        if (isHuiwen(A+B)){
            count++;
        }

        System.out.println(count);

    }

    public static boolean isHuiwen(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            stack.push(c);
        }

        for (char c : s.toCharArray()) {
            if (stack.pop() != c) {
                return false;
            }
        }
        return true;
    }
}
