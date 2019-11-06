package day02;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/6
 * Description: 空瓶子换汽水
 */
public class DrinkWater {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        while (scanner.hasNext()){
            n = scanner.nextInt();

            if(n == 2){
                System.out.println(1);
            }else{
                int result = 0;
                int re;
                while(n>=3){
                    re = n%3;
                    n = n/3;
                    result += n;
                    n += re;
                    if(n==2){
                        result+=1;
                        break;
                    }
                }
                System.out.println(result);
            }
        }
    }
}
