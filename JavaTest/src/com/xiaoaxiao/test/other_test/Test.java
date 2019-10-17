package com.xiaoaxiao.test.other_test;

import java.util.ArrayList;

/**
 * Created by xiaoaxiao on 2019/8/25
 * Description:
 */
public class Test {
    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
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

            int m = 0;
            if (i + 1 < matrix.length - i && j - 2 >= i && k - 2 > i) {
                for (m = k - 2; m > i; m--) {
                    list.add(matrix[m][l + 1]);
                }
            }else {
                break;
            }
        }
        return list;
    }

    public static void main(String[] args) {
//        int[][] matrix = {
//                {1, 2, 3, 4},
//                {5, 6, 7, 8},
//                {9, 10, 11, 12},
//                {13, 14, 15, 16}
//        };
        int[][] matrix = {
                {1,2},
                {3,4},
                {5,6},
                {7,8},
                {9,10}
        };
        ArrayList<Integer> list = printMatrix(matrix);
        System.out.println(list);
    }
}
