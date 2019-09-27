package com.xiaoaxiao.test.other_test;

import java.util.Arrays;

/**
 * Created by xiaoaxiao on 2019/9/27
 * Description:
 */

enum Week{
    Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday
}

public class EnumDemo {
    public static void main(String[] args) {
        Week week = Week.Thursday;
        System.out.println(week);
        System.out.println(Arrays.toString(Week.values()));
        Week w = Week.valueOf("Monday ");
        System.out.println(w);
    }
}
