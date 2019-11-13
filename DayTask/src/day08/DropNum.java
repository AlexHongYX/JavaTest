package day08;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/13
 * Description: 每隔两个数删除一个数，求最后一个被删除的数的原始下标
 *      利用队列的入队出队，出两次，入两次；再出一次，不入==每隔两个数字删除一个数
 */

public class DropNum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int n = scanner.nextInt();
            if (n > 1000){
                n = 999;
            }
            Queue<Integer> queue = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                queue.offer(i);
            }
            while (queue.size()!=1){
                queue.offer(queue.poll());
                queue.offer(queue.poll());
                queue.poll();
            }
            System.out.println(queue.poll());

        }
    }
}
