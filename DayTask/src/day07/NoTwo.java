package day07;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/12
 * Description: 一个网格中欧几里得距离不为2
 */
public class NoTwo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int w = scanner.nextInt();
        int h = scanner.nextInt();

        int[][] arr = new int[w][h];

        for (int i =0;i<w;i++){
            for (int j=0;j<h;j++){
                if(arr[i][j]==-1){
                    continue;
                }
                if (j+2<h&&arr[i][j+2]==0){
                    arr[i][j+2] = -1;
                }
                if(j-2>=0&&arr[i][j-2]==0){
                    arr[i][j-2] = -1;
                }
                if(i+2<w&&arr[i+2][j]==0){
                    arr[i+2][j] = -1;
                }
                if(i-2>=0&&arr[i-2][j]==0){
                    arr[i-2][j] = -1;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if(arr[i][j]==0){
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
