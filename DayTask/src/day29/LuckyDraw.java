package day29;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/12/9
 * Description: 抽奖没有人抽到的概率
 *      1、每人将自己名字放入抽奖箱
 *      2、每人从抽奖箱中取一个纸条
 *      3、若纸条是自己，则抽上，反之，没抽上
 *
 *      错排算法/所有可能(n!)
 */
public class LuckyDraw {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            int n = scanner.nextInt();
            double ret = isLucky(n);
            System.out.println((int)Math.floor(ret*100)+"."+(int)(ret*1000)%10+(int)(ret*10000)%10+"%");
        }
    }

    public static double isLucky(int n){
        return 1-1.0/n;
    }
}
