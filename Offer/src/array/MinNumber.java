package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/11/8
 * Description: 将数组排成最小的数字
 */
public class MinNumber {

    public String PrintMinNumber(int [] numbers) {
        List<Integer> list = new ArrayList<>();

        for(int i=0;i<numbers.length;i++){
            list.add(numbers[i]);
        }

        /*
        * 比较器实际上在对一个数组进行比较时
        *   前面的数是o1，后面的数是o2
        *   sort()方法当返回值>0时，表示需要交换o1和o2
        *   返回值<=0，则不需要交换
        *
        *   因此
        *       升序：return o1-o2;        若o1>o2，返回正数，需要交换，说明若前面数比后面数大时需要交换
        *       降序：return o2-o1;        若o1<o2，返回正数，需要交换，说明若前面数比后面数小时需要交换
        * */
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // str1=o1+o2，这是原本的排列
                String str1 = "" + o1 + o2;
                // str2=o2+o1，这是逆过来的排序
                String str2 = "" + o2 + o1;

                // 若原本的排序组成的数>逆过来的排序组成的数，会交换这两个数顺序
                return Integer.valueOf(str1) - Integer.valueOf(str2);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Integer i:list){
            sb.append(i);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        MinNumber minNumber = new MinNumber();
        int[] arr = {3,32,321};
        System.out.println(minNumber.PrintMinNumber(arr));
    }
}
