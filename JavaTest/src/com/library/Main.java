package com.xiaoaxiao.library;

import com.xiaoaxiao.library.classes.User;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/10/9
 * Description: 程序入口
 */
public class Main {

    public static void main(String[] args) {


        User currentUser = User.login();
        boolean isQuit;
        do {
            currentUser.menu();
            isQuit = currentUser.input();
        }while (!isQuit);
        System.out.println("欢迎下次光临");
    }
}
