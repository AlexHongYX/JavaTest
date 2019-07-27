package com.xiaoaxiao.test.io_test.system_test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/7/26
 * Description: Scanner操作文件
 */
public class ScannerTest3 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(
                new File("C:"+File.separator+"Users"+File.separator+
            "xiaoaxiao"+File.separator+"Desktop"+File.separator+"Test.java")));

        if (scanner.hasNext()){
            System.out.println(scanner.next());
        }
    }
}
