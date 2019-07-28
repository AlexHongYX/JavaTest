package com.xiaoaxiao.test.collection_test.properties_test;

import java.util.Properties;

/**
 * Created by xiaoaxiao on 2019/7/28
 * Description: Properties类测试
 */
public class PropertiesTest1 {

    public static void main(String[] args) {
        Properties properties = new Properties();

        properties.setProperty("username","xiaoaxiao");
        properties.setProperty("password","123456");

        System.out.println(properties.getProperty("username"));
        System.out.println(properties.getProperty("password"));

        // 如果key值不存在，返回null
        System.out.println(properties.getProperty("hello"));

        // getProperty的重载，设置默认值（若不存在时）
        System.out.println(properties.getProperty("hello","world"));
    }
}
