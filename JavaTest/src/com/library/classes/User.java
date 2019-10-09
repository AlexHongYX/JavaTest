package com.xiaoaxiao.library.classes;

import com.xiaoaxiao.library.databases.BookShelf;

import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/10/9
 * Description: 用户类
 */
public abstract class User {
    private String UserId;
    private String name;

    public User(String userId, String name) {
        UserId = userId;
        this.name = name;
    }

    public static User login() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入角色");
        String role = scanner.nextLine();

        System.out.println("请输入id");
        String id = scanner.nextLine();

        System.out.println("请输入名字");
        String name = scanner.nextLine();

        User user;
        if (role.equals("老师")){
            user = new Teacher(id,name);
        }else {
            user = new Student(id,name);
        }
        return user;
    }

    public abstract void menu() ;

    public abstract boolean input() ;

    protected void searchBook(){
        Action.AllBook();
    }

}
