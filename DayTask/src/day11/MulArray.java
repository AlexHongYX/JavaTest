package day11;

/**
 * Created by xiaoaxiao on 2019/11/19
 * Description: 构造乘积数组
 */
public class MulArray {

    public static void main(String[] args) {

        int[] A = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] result = multiply(A);

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    private static int[] multiply(int[] A) {
        int length = A.length;
        int[] B = new int[length];
        int[] C = new int[length];
        int[] D = new int[length];

        C[0] = 1;
        C[1] = A[0];
        for (int i = 2; i < length; i++) {
            C[i] = A[i - 1] * C[i - 1];
        }

        D[length-1] = 1;
        D[length-2] = A[length-1];
        for (int i = length - 3; i >= 0; i--){
            D[i] = A[i+1] * D[i+1];
        }


        for (int i = 0; i < length; i++) {
            B[i] = C[i]*D[i];
        }
        return B;
    }
}
