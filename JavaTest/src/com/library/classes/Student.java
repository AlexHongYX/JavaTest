package com.xiaoaxiao.library.classes;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/10/9
 * Description:
 */
public class Student extends User {
    public Student(String id, String name) {
        super(id, name);
    }

    @Override
    public void menu() {
        System.out.println("Student menu");
        System.out.println("0、quit");
        System.out.println("1、上架");
        System.out.println("2、查阅图书");
        System.out.println("3、查阅记录");
    }

    @Override
    public boolean input() {
        Scanner scanner = new Scanner(System.in);
        int selected = scanner.nextInt();
        switch (selected){
            case 0:return true;
            case 1:break;
            case 2:break;
            case 3:break;
        }
        return false;
    }
}
