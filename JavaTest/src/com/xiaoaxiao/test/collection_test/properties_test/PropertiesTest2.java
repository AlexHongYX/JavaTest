package com.xiaoaxiao.test.collection_test.properties_test;

import java.io.*;
import java.util.Properties;

/**
 * Created by xiaoaxiao on 2019/7/28
 * Description: Properties类与.properties文件的交互
 */
public class PropertiesTest2 {

    public static void main(String[] args) throws IOException {

        File file = new File("C:"+File.separator+"Users"+File.separator
                +"xiaoaxiao"+File.separator+"Desktop"+File.separator
                +"Test.txt");

//        // 向文件中写入数据
//        Properties properties = new Properties();
//        properties.setProperty("username","xiaoaxiao");
//        properties.setProperty("password","123456");
//        properties.store(new FileOutputStream(file),"this is a test");

        // 从文件中读取数据
        Properties properties1 = new Properties();
        properties1.load(new FileInputStream(file));
        System.out.println("username:"+properties1.getProperty("username"));
        System.out.println("password:"+properties1.getProperty("password"));
    }
}
