package day08;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/13
 * Description: 找到一个数组中最小的k个值
 */
public class MinK {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int[] arr = new int[str.split(" ").length];
        int i = 0;
        for (String s : str.split(" ")) {
            arr[i++] = Integer.valueOf(s);
        }

        int k = arr[arr.length - 1];

        int[] topK = new int[k];

        for (int j = 0; j < k; j++) {
            topK[j] = arr[j];
        }

        heapSort(topK);

        for (int j = k; j < arr.length-1; j++) {
            if (arr[j]<topK[0]){
                topK[0] = arr[j];
                adjustDown(topK,topK.length,0);
            }
        }



        for (int j = topK.length-1;j>0;j--){
            int tmp = topK[j];
            topK[j] = topK[0];
            topK[0] = tmp;

            adjustDown(topK,j,0);
        }

        for (int j = 0; j < topK.length; j++) {
            System.out.println(topK[j]);
        }

    }

    public static void heapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustDown(array, array.length, i);
        }
    }

    private static void adjustDown(int[] array, int length, int i) {
        int parent = i;
        int child = parent * 2 + 1;
        while (child < length) {
            if (child + 1 < length && array[child + 1] > array[child]) {
                child++;
            }

            if (array[child] > array[parent]) {
                int tmp = array[child];
                array[child] = array[parent];
                array[parent] = tmp;

                parent = child;
                child = parent * 2 + 1;
            }else {
                break;
            }
        }
    }
}
