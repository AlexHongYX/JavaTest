package day06;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/12
 * Description: 计算糖果，已知A-B，B-C，A+B，B+C的值求ABCD
 */
public class CandyValue {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int aSubB = scanner.nextInt();
        int bSubC = scanner.nextInt();
        int aplusB = scanner.nextInt();
        int bplusC = scanner.nextInt();

        int A = 0;
        int B1 = 0;
        int B2 = 0;
        int C = 0;
        boolean isZheng = true;

        if ((aSubB+aplusB)%2==0&&(bSubC+bplusC)%2==0&&(bplusC-bSubC)%2==0&&(aplusB-aSubB)%2==0){
            A = (aSubB+aplusB)/2;
            // B使用不同的算法可能的结果值不同
            // B是连通四个式子的核心。
            // A和C的值只能由一种方式确定，而B的值可以使用B,C之间的关系确定，也可以使用B,A之间的关系确定
            // 而是否真正存在三个数满足该关系，将A,C算出后，使用两种不同的算法算B
            // 若两种不同算法得出的B结果相同，则说明存在这三个数
            // 若不同，则说明不存在这三个数
            B1 = (bSubC+bplusC)/2;
            B2 = (aplusB-aSubB)/2;
            C = (bplusC-bSubC)/2;
        }else {
            isZheng = false;
        }

        if (isZheng&&B1==B2){
            System.out.println(A+" "+B1+" "+C);
        }else {
            System.out.println("No");
        }

    }
}
