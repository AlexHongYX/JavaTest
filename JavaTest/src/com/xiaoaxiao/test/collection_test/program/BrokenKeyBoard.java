package com.xiaoaxiao.test.collection_test.program;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by xiaoaxiao on 2019/9/8
 * Description:旧键盘 (20)
 *      https://www.nowcoder.com/questionTerminal/f88dafac00c8431fa363cd85a37c2d5e
 */
public class BrokenKeyBoard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expected = scanner.nextLine();
        String actual = scanner.next();

        // 将实际的字符放入set中
        Set<Character> set = new HashSet<>();
        // 将实际字符转为全大写放入到Set中
        for (char c:actual.toUpperCase().toCharArray()){
            set.add(c);
        }

        // 有个小问题——坏掉的键盘键只需要输出一次，所以应该由Set保存
        // (否则每遇到一个坏键对应的字符就会打印一次)
        Set<Character> brokenKey = new HashSet<>();
        // 遍历全大写的实际字符串
        for (char c:expected.toUpperCase().toCharArray()){
            // 若c没在set中，且c是第一次出现，则打印c
            if (!set.contains(c)&&!brokenKey.contains(c)){
                System.out.print(c);
                brokenKey.add(c);
            }
        }
    }
}
