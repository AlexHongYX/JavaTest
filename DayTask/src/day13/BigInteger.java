package day13;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by xiaoaxiao on 2019/11/20
 * Description: 高精度相加
 */
public class BigInteger {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String num1 = scanner.next();
            String num2 = scanner.next();

            String odd1 = reverse(num1);
            String odd2 = reverse(num2);

//        System.out.println(odd1);
//        System.out.println(odd2);
            String ret = reverse(AddLongInteger(odd1,odd2));

            System.out.println(ret);
        }

    }

    private static String AddLongInteger(String odd1, String odd2) {
        int yu = 0;
        StringBuilder sb = new StringBuilder();
        while (odd1.length()!=0&&odd2.length()!=0){
            int value = (odd1.charAt(0)-'0')+(odd2.charAt(0)-'0');

            sb.append((value+yu)%10);
            yu = (value+yu)/10;


            odd1 = odd1.substring(1);
            odd2 = odd2.substring(1);
        }

        while (odd1.length()!=0){
            int value = (odd1.charAt(0)-'0');


            sb.append((value+yu)%10);
            yu = (value+yu)/10;

            odd1 = odd1.substring(1);
        }
        while (odd2.length()!=0){
            int value = (odd2.charAt(0)-'0');

            sb.append(value%10+yu);

            yu = (value+yu)/10;
            odd2 = odd2.substring(1);
        }
        if (yu!=0){
            sb.append(yu);
        }
        return sb.toString();
    }

    private static String reverse(String str) {
        Stack<Character> stack = new Stack<>();

        for (char c:str.toCharArray()){
            stack.push(c);
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }

        return sb.toString();
    }


}
