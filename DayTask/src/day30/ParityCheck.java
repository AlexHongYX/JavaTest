package day30;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/12/9
 * Description: 奇偶校验
 * 字符串中每个字符的int值，若二进制中1的个数为奇，则最前面为0，若为偶，则最前面为1
 */
public class ParityCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            for (char c : str.toCharArray()) {
                String ret = parity(c);
                System.out.println(ret);
            }
        }
    }

    /**
     * 奇偶校验方法
     *
     * @param c
     * @return
     */
    private static String parity(char c) {
        StringBuilder sb = new StringBuilder();
        int val = (int)c;
        int count = 0;
        while (val > 0) {
            int i = val%2;
            if (i==1){
                count++;
            }
            sb.insert(0,i);
            val/=2;
        }
        // 前面全补0
        while (sb.length()<7){
            sb.insert(0,'0');
        }

        // 最高位根据奇偶数量进行判断
        if (count%2==0){
            sb.insert(0,'1');
        }else {
            sb.insert(0,'0');
        }
        return sb.toString();
    }
}
