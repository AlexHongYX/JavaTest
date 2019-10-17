package com.xiaoaxiao.test.other_test;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/10/12
 * Description:
 */
public class WordTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashSet<String> set = new HashSet<>();

        while (scanner.hasNext()){
            set.add(scanner.next());
        }

        System.out.println(set.size());
    }
}
