package day16;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by xiaoaxiao on 2019/11/24
 * Description: k次洗牌后的结果
 *      思路很简单，用两个栈，前栈和后栈，分别进行入栈出栈即可
 *              这个操作做k次即可
 */
public class WashCard {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();

        while (num != 0) {

            int n = scanner.nextInt();
            int k = scanner.nextInt();

//            最终还是选择在一行中输入所有的值
//            输入：3 3 1 1 2 3 4 5 6 3 2 1 2 3 4 5 6 2 2 1 1 1 1
//            输出：
//              1 4 2 5 3 6
//              1 5 4 3 2 6
//              1 1 1 1

            int[] arr = new int[n*2];
            for (int i = 0; i < n*2; i++) {
                arr[i] = scanner.nextInt();
            }

            // 进行k次操作
            // 前栈后栈入栈出栈即可
            while (k != 0) {
                Stack<Integer> stackFront = new Stack<>();
                Stack<Integer> stackAfter = new Stack<>();

                for (int i = 0; i < n; i++) {
                    stackFront.push(arr[i]);
                }
                for (int i = n; i < 2 * n; i++) {
                    stackAfter.push(arr[i]);
                }
                for (int i = 2 * n - 1; i >= 0; ) {
                    arr[i--] = stackAfter.pop();
                    arr[i--] = stackFront.pop();
                }
                k--;
            }

            for (int i = 0; i < 2 * n; i++) {
                System.out.print(arr[i]);
                if (i != 2 * n - 1) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            num--;
        }
    }
}
