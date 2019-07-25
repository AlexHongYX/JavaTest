package com.xiaoaxiao.test.io_test;

import java.io.File;
import java.util.Date;

/**
 * Created by xiaoaxiao on 2019/7/25
 * Description: 目录列表
 */
public class DirectoryTest {

    public static void main(String[] args) {
//        System.out.println("主线程开始");
        long start = new Date().getTime();
        //将这种耗时阻塞操作放在子线程中运行
//        new Thread(()->{
            File file = new File("F:" + File.separator + "Java");
            listAllFiles(file);
//        }).start();
        long end = new Date().getTime();
        System.out.println("总耗时："+(end-start)+"ms");
//        System.out.println("主线程结束");
    }

    private static void listAllFiles(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null){ //该文件夹中没有文件
                for (File file1 : files) {
                    listAllFiles(file1);
                }
            }
        } else {
            System.out.println(file);
        }
    }
}
