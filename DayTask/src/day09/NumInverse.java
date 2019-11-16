package day09;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by xiaoaxiao on 2019/11/14
 * Description: 数字逆置
 */
public class NumInverse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        String str = String.valueOf(num);
        String newStr = reverse(str);
        System.out.println(newStr);
    }

    private static String reverse(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
