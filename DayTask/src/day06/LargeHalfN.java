package day06;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/12
 * Description: n个数中出现次数大于等于n/2的数
 */
public class LargeHalfN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String str = scanner.nextLine();
        String[] strings = str.split(" ");
        Map<String,Integer> map = new HashMap<>();

        for (String s:strings){
            if(map.containsKey(s)){
                map.put(s,map.get(s)+1);
            }else {
                map.put(s,1);
            }
        }

        int ret = 0;
        for (Map.Entry<String,Integer> entry:map.entrySet()){
            if(entry.getValue()>=strings.length/2) {
                ret = Integer.valueOf(entry.getKey());
                break;
            }
        }

        System.out.println(ret);
    }
}
