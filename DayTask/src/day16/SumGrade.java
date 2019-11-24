package day16;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/24
 * Description: 统计成绩相同的人数
 *      题是水题，但是要注意输入的格式
 *          scanner.hasNext()的使用
 */
public class SumGrade {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);



        while (scanner.hasNext()){

            int num = Integer.valueOf(scanner.nextLine());
            if (num==0){
                break;
            }

            String str = scanner.nextLine();
            String[] arr = str.split(" ");
            String grade = scanner.nextLine();

            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                if (grade.equals(arr[i])){
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
