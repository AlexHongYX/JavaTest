package array;

/**
 * Created by xiaoaxiao on 2019/11/14
 * Description: 构建乘积数组
 */
public class MulArray {
    public static int[] multiply(int[] A) {
        int[] C = new int[A.length];
        int[] D = new int[A.length];
        int mulC = 1;
        for(int i = 1;i<A.length;i++){
            mulC = mulC*A[i-1];
            C[i] = mulC;
        }
        int mulD = 1;
        for(int i = A.length-2;i>=0;i--){
            mulD = mulD*A[i];
            D[i] = mulD;
        }

        int[] B = new int[A.length];
        B[0] = D[0];
        B[A.length-1] = C[A.length-1];
        for (int i = 1; i < A.length-1; i++) {
            B[i] = C[i]*D[i];
        }
        return B;
    }

    public static void main(String[] args) {
        int[] A = {1,2,3,4,5};
        int[] B = multiply(A);
        for (int i = 0; i < B.length; i++) {
            System.out.print(B[i]+" ");
        }
    }
}
