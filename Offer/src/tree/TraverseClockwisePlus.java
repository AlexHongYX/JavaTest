package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/9/30
 * Description: 顺时针遍历二维数组Plus——多加了个自定义输入二维数组
 * https://www.nowcoder.com/practice/ce0c22a435114108bd9acc75f81b5802
 */
public class TraverseClockwisePlus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            if (m == -1 && n == -1) {
                break;
            }
            int[][] matrix = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }

            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < matrix.length / 2 + 1; i++) {
                int j = 0;
                if (i < matrix[i].length-i){
                    for (j = i; j < matrix[i].length - i; j++) {
                        list.add(matrix[i][j]);
                    }
                }else {
                    break;
                }

                int k = 0;
                if (i + 1 < matrix.length - i) {
                    for (k = i + 1; k < matrix.length - i; k++) {
                        list.add(matrix[k][j - 1]);
                    }
                }else {
                    break;
                }
                int l = 0;
                if (i + 1 < matrix.length - i && j - 2 >= i) {
                    for (l = j - 2; l >= i; l--) {
                        list.add(matrix[k - 1][l]);
                    }
                }else {
                    break;
                }

                int h = 0;
                if (i + 1 < matrix.length - i && j - 2 >= i && k - 2 > i) {
                    for (h = k - 2; h > i; h--) {
                        list.add(matrix[h][l + 1]);
                    }
                }else {
                    break;
                }
            }

            System.out.println(list.toString());

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i));
                if (i!=list.size()-1){
                    sb.append(",");
                }
            }

            System.out.println(sb.toString());
        }


    }
}
