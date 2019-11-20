package day13;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/20
 * Description: 画个正方形
 */
public class Square {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();
        int count = Integer.valueOf(str.split(" ")[0]);
        char c = (str.split(" ")[1].toCharArray())[0];

        if (count<3){
            for (int i = 0; i < count; i++) {
                System.out.print(c);
            }
            System.out.println();
        }else {
            for (int i = 0; i < count; i++) {
                System.out.print(c);
            }
            System.out.println();


            for (int i = 0; i < Math.round(count*1.0/2)-2; i++) {
                System.out.print(c);
                for (int j = 0; j < count-2; j++) {

                    System.out.print(" ");
                }
                System.out.println(c);
            }


            for (int i = 0; i < count; i++) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
