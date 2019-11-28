package test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/11/27
 * Description:
 */
public class InsertList {
    private static List<Integer> dataList = new LinkedList<>();

    public static void add(Integer data){
        dataList.add(data);
        dataList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
    }

    public static void main(String[] args) {
        add(6);
        System.out.println(dataList);
        add(4);
        System.out.println(dataList);
        add(3);
        System.out.println(dataList);
        add(2);
        System.out.println(dataList);
        add(8);
        System.out.println(dataList);

    }
}
