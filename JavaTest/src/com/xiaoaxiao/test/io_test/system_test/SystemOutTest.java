package com.xiaoaxiao.test.io_test.system_test;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by xiaoaxiao on 2019/7/26
 * Description: System.out可以为OutputStream实例化，
 *              这个时候的OutputStream输出的位置将变为屏幕。
 */
public class SystemOutTest {

    public static void main(String[] args) {
        OutputStream out = System.out;

        try {
            out.write("你好中国".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
