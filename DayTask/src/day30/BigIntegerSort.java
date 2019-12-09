package day30;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/12/9
 * Description: 大整数排序
 */
public class BigIntegerSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int N = scanner.nextInt();
            scanner.nextLine();
            String[] strings = new String[N];
            for (int i = 0; i < strings.length; i++) {
                strings[i] = scanner.next();
            }
            // 自定义比较器
            Arrays.sort(strings, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.length()-o2.length();
                }
            });

            for (int i = 0; i < strings.length; i++) {
                System.out.println(strings[i]);
            }

            Arrays.sort(strings, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (o1.length()==o2.length()){
                        for (int i = 0; i < o1.length(); i++) {
                            if (o1.charAt(i)<o2.charAt(i)){
                                return -1;
                            }else if (o1.charAt(i)>o2.charAt(i)){
                                return 1;
                            }
                        }
                    }
                    return 1;
                }
            });

            // 自定义比较器
            Arrays.sort(strings, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (o1.length()!=o2.length()){
                        return o1.length()-o2.length();
                    }
                    if (o1.length()==o2.length()){
                        for (int i = 0; i < o1.length(); i++) {
                            if (o1.charAt(i)<o2.charAt(i)){
                                return -1;
                            }else if (o1.charAt(i)>o2.charAt(i)){
                                return 1;
                            }
                        }
                    }
                    return 1;
                }
            });



            System.out.println("======================================================");
            for (int i = 0; i < strings.length; i++) {
                System.out.println(strings[i]);
            }
        }
    }
}
