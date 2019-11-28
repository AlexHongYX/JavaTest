package test;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by xiaoaxiao on 2019/11/28
 * Description: 单词逆置
 */
public class Reverse {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(ReverseWord(str));
    }

    private static String ReverseWord(String str) {
        String[] arr = str.split(" ");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            stack.push(arr[i]);
        }

        String[] ret = new String[arr.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ret.length; i++) {
            sb.append(ret[i]);
            if (i!=ret.length-1){
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
