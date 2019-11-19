package day11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/11/19
 * Description: 数字出现次数
 */
public class NumAppear {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String num = scanner.next();

        Map<Integer,Integer> map = new HashMap<>();
        for (Character c : num.toCharArray()){
            Integer i = c-'0';
            if(map.containsKey(i)){
                map.put(i,map.get(i)+1);
            }else {
                map.put(i,1);
            }
        }

//        System.out.println(map);

        int size = map.size();
        String[] strings = new String[size];
        int i = 0;
        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            strings[i] = entry.getKey()+":"+entry.getValue();
            i++;
        }

        Arrays.sort(strings);

        for (int j = 0; j < size; j++) {
            System.out.println(strings[j]);
        }
    }
}
