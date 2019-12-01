package day21;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/12/1
 * Description: 解密：密码是原文字母往后走5位，将密码破译，将所有字母往前走5位即可，注意循环
 */
public class LetterChange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String password = scanner.nextLine();
            StringBuilder sb = new StringBuilder();
            for (char c : password.toCharArray()){
                sb.append(getChar(c));
            }
            System.out.println(sb.toString());
        }
    }

    private static char getChar(char c){
        if (c==' '){
            return ' ';
        }
        int val = (int)c;
        int ret = val-5;
        return ret>='A'?(char)ret:(char)(ret+26);
    }
}
