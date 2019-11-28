package test;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/28
 * Description:
 */
public class Transfer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()){
            switch (c){
                case '玖':sb.append("9"); break;
                case '捌':sb.append("8"); break;
                case '柒':sb.append("7"); break;
                case '陆':sb.append("6"); break;
                case '伍':sb.append("5"); break;
                case '肆':sb.append("4"); break;
                case '叁':sb.append("3"); break;
                case '贰':sb.append("2"); break;
                case '壹':sb.append("1"); break;
                case '零':sb.append("0"); break;
            }
        }
        System.out.println(sb.toString());
    }
}
