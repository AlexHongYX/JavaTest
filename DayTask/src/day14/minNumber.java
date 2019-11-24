package day14;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/21
 * Description: 组成最小数，给任意多个[0,9]，组成值最小额，0不能做第一个
 *  顺序代表数字0-9的个数，也就是说 2 2 0 0 0 3 0 0 1 0：表示数字0有2个，数字1有2个，数字2有0个...
 */
public class minNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String[] strs = scanner.nextLine().split(" ");
            int length = strs.length;
            int[] arr = new int[length];
            int count = 0;
            for (int i = 0; i < length; i++) {
                arr[i] = Integer.valueOf(strs[i]);
                count += arr[i];
            }

            int[] array = new int[count];
            int index = 0;
            for (int i = 0; i < length; i++) {
                while (arr[i]!=0){
                    array[index++] = i;
                    arr[i]--;
                }
            }

            Arrays.sort(array);

            for (int i = 0; i < index; i++) {
                if (array[i]!=0){
                    int tmp = array[i];
                    array[i] = array[0];
                    array[0] = tmp;
                    break;
                }
            }

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < index; i++) {
                sb.append(array[i]);
            }

            System.out.println(sb.toString());
        }
    }
}
