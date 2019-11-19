package day12;

/**
 * Created by xiaoaxiao on 2019/11/19
 * Description: 不引入第三个变量交换
 */
public class NumSwap {
    public static void main(String[] args) {
        int a = 5;
        int b = 7;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);
    }
}
