package day24;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/12/4
 * Description: 数组基本题
 */
public class HighestScore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = scanner.nextInt();
            }

            scanner.nextLine();

            while (m!=0){
                String str = scanner.nextLine();
                String[] strings = str.split(" ");
                if (strings[0].equals("Q")){
                    int start = Integer.valueOf(strings[1]);
                    int end = Integer.valueOf(strings[2]);
                    if (start>end){
                        int tmp = start;
                        start = end;
                        end = tmp;
                    }
                    start--;
                    System.out.println(getMax(arr,start,end));
                }else {
                    int key = Integer.valueOf(strings[1])-1;
                    int value = Integer.valueOf(strings[2]);
                    arr[key] = value;
                }
                m--;
            }
        }
    }

    public static int getMax(int[] arr,int start,int end){
        int max = arr[start];
        for (int i = start+1; i < end; i++) {
            if (arr[i]>max){
                max = arr[i];
            }
        }
        return max;
    }
}
