package day10;

/**
 * Created by xiaoaxiao on 2019/11/16
 * Description: 机器人走方格，只能向右或向下走，从左上角走到右下角有几种走法
 */
public class Robot {

    public static int countWays(int x, int y) {
        int[][] arr = new int[x][y];

        for (int i = 1; i < x; i++) {
            arr[i][0] = 1;
        }
        for (int j = 1; j < y; j++) {
            arr[0][j] = 1;
        }

        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                arr[i][j] = arr[i-1][j] + arr[i][j-1];
            }
        }

        return arr[x-1][y-1];
    }

    public static void main(String[] args) {
        System.out.println(countWays(2,3));
    }
}
