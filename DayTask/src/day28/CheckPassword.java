package day28;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/12/9
 * Description: 密码验证
 *      1、长度>8
 *      2、大写，小写，数字，其他符号，四种中占三种
 *      3、不能有相同长度超2的子串重复
 */
public class CheckPassword {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String str = scanner.nextLine();
            if (isPassword(str)){
                System.out.println("OK");
            }else {
                System.out.println("NG");
            }
        }
    }

    private static boolean isPassword(String str) {
        if (str.length()<=8){
            return false;
        }
        int bigWord = 0;
        int smallWord = 0;
        int num = 0;
        int other = 0;
        for (char c : str.toCharArray()){
            if (c>='A'&&c<='Z'){
                bigWord = 1;
            }else if (c>='a'&&c<='z'){
                smallWord = 1;
            }else if (c>='0'&&c<='9'){
                num = 1;
            }else {
                other = 1;
            }
        }
        if (bigWord+smallWord+num+other<3){
            return false;
        }

        // 投机取巧的方法——只需要判断有没有长度为3的子串重复即可
        // 若存在长度比3大的子串重复，那么一定存在长度为3的子串重复
        for (int i = 0; i < str.length()-3; i++) {
            // 从前向后遍历str中的每一个长度为3的子串，并判断该子串的后面字符串中是否存在该子串
            // 若存在则说明当前长度为3的子串存在多个，若不存在则说明该子串不重复
            if (str.substring(i+3).contains(str.substring(i,i+3))){
                return false;
            }
        }
        return true;
    }
}
