package test;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/28
 * Description:
 */
public class Contains {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String text = scanner.nextLine();
            String label = scanner.nextLine();

            char[] textArrays = text.toCharArray();

            // 去掉前后的[]
            label = label.substring(1,label.length()-1);

            String[] strings = label.split(",");
            int countWord = 0;
            int countNum = 0;
            for (String c : strings){
                if (c.equals("1")) {
                    countWord++;
                } else if (c.equals("3")) {
                    countNum++;
                }
            }
            String[] stringWord = new String[countWord];
            int wordIndex = 0;
            String[] stringNum = new String[countNum];
            int numIndex = 0;

            for (int i = 0; i < strings.length; ) {
                if(strings[i].equals("1")){
                    StringBuilder sb = new StringBuilder();
                    sb.append(textArrays[i]);
                    i++;
                    while (i<strings.length&&strings[i].equals("2")){
                        sb.append(textArrays[i]);
                        i++;
                    }
                    stringWord[wordIndex++] = sb.toString();
                    continue;
                }
                if (strings[i].equals("3")) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(textArrays[i]);
                    i++;
                    while (i<strings.length&&strings[i].equals("4")){
                        sb.append(textArrays[i]);
                        i++;
                    }
                    stringNum[numIndex++] = sb.toString();
                    continue;
                }
                i++;
            }

            System.out.print("关键字：[");
            for (int i = 0; i < wordIndex; i++) {
                System.out.print("'"+stringWord[i]+"'");
                if (i!=wordIndex-1){
                    System.out.print(",");
                }
            }
            System.out.print("],串：[");
            for (int i = 0; i < numIndex; i++) {
                System.out.print("'"+stringNum[i]+"'");
                if (i!=numIndex-1){
                    System.out.print(",");
                }
            }
            System.out.println("]");
        }
    }
}
