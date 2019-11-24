package day14;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/22
 * Description: 尼科彻斯定理：任何一个数m的立方都能写成m个连续奇数之和
 */
public class Nikeqiesi {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int num = scanner.nextInt();
//
//        if (num==0){
//            System.out.println(0);
//        }else {
//            System.out.println(GetSequeOddNum(num));
//        }
//
//    }
//
//    /**
//     * 如果m*m是奇数，说明m就是个奇数，那么就是m*m为中间，左边右边往左右延伸，由于m是奇数，因此左右对称
//     * 如果m*m是偶数，说明m就是个偶数，那么以m*m为对称轴，向左向右延伸，由于m是偶数，因此左右也对称
//     *
//     * @param m 形参
//     * @return 返回String
//     */
    public static String GetSequeOddNum(int m) {

        int[] str = new int[m];
        // m是偶数
        if (m % 2 == 0){
            str[m/2] = m*m+1;
        }else {
            str[m/2] = m*m;
        }
        // 前半部分
        for (int i = m/2-1; i >= 0; i--) {
            str[i] = str[i+1]-2;
        }
        // 后半部分
        for (int i = m/2+1; i < m; i++) {
            str[i] = str[i-1]+2;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length ;i++) {
            sb.append(str[i]);
            if (i!=str.length-1){
                sb.append("+");
            }
        }
        return sb.toString();
    }
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int num = scanner.nextInt();

    System.out.println(GetSequeOddNum(num));
}

    /**
     * 如果m*m是奇数，说明m就是个奇数，那么就是m*m为中间，左边右边往左右延伸，由于m是奇数，因此左右对称
     * 如果m*m是偶数，说明m就是个偶数，那么以m*m为对称轴，向左向右延伸，由于m是偶数，因此左右也对称
     *
     * @param m 形参
     * @return 返回String
     */
//    public static String GetSequeOddNum(int m) {
//
//        int[] str = new int[m];
//
//        str[0] = m*m-m+1;
//        for(int i = 1;i<m;i++){
//            str[i] = str[i-1]+2;
//        }
//
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < m ;i++) {
//
//            if (i!=m-1){
//                sb.append(str[i]+"+");
//            }
//        }
//        sb.append(str[m-1];
//        return sb.toString();
//
//
////        StringBuffer sb = new StringBuffer() ;
////        for(int k = 0 ; k < n ; k++){
////            if(k != n - 1)
////                sb.append(array[k] + "+") ;
////        }
////        sb.append(array[n - 1]) ;
////
////        return sb.toString();
//    }
}
