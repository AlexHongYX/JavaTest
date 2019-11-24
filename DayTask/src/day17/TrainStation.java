package day17;

import java.util.*;

/**
 * Created by xiaoaxiao on 2019/11/24
 * Description: 火车进站的所有序列
 *      所有数字的全排序+判断序列是否可以是出栈序列
 */
public class TrainStation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            int n = Integer.valueOf(scanner.nextLine());
            int[] arr = new int[n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = scanner.nextInt();
            }

            Arrays.sort(arr);

            // 找到这组数的全排列(这n个数的所有可能出现的情况)
            Queue<int[]> queue = new LinkedList<>();

            allSort(arr,0,arr.length-1,queue);

            Set<String> set = new HashSet<>();
            while (!queue.isEmpty()){
                int[] val = queue.poll();
                if (isPop(arr,val)){
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < val.length-1; i++) {
                        sb.append(val[i]).append(" ");
                    }
                    sb.append(val[val.length-1]);
                    set.add(sb.toString());
                }
            }

           for (String str : set){
               System.out.println(str);
           }
        }
    }

    /**
     * 当前序列是否为出栈序列
     * @param arr
     * @param pop
     * @return
     */
    public static boolean isPop(int[] arr,int[] pop){
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            stack.push(arr[i]);
            while (!stack.empty()&&pop[index]==stack.peek()){
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 序列全排序
     * @param arr
     * @return
     */
    public static void allSort(int[] arr,int start,int end,Queue<int[]> queue){
        if (start==end){
            queue.add(arr.clone());
        }else {
            for (int i = start; i <= end ; i++) {
                swap(arr,start,i);
                allSort(arr,start+1,end,queue);
                swap(arr,start,i);
            }
        }

    }

    private static void swap(int[] arr, int start, int i) {
        int tmp = arr[start];
        arr[start] = arr[i];
        arr[i] = tmp;
    }
}
