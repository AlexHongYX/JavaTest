package day03;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/7
 * Description: 字符串中的最长数字串
 */
public class LongNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.next();
        StringBuilder sb = new StringBuilder();
        String ret = "";
        int longSize = 0;
        for(char c:str.toCharArray()){
            if (c>=48&&c<=57){
                sb.append(c);
            }else{
                if(sb.length()>longSize){
                    ret = sb.toString();
                    longSize = sb.length();
                }
                sb.delete(0,sb.length());
            }
        }
        if (sb.length()>longSize){
            ret = sb.toString();
        }

        System.out.println(ret);
    }
}
