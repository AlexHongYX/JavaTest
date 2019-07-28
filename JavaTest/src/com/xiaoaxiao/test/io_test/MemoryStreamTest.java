package com.xiaoaxiao.test.io_test;

import java.io.*;

/**
 * Created by xiaoaxiao on 2019/7/25
 * Description: 内存流实现单词全大写
 */
public class MemoryStreamTest {

    public static void main(String[] args) throws IOException {
        String msg = "hello";

        // 使用字节流（不是中文），一个英文字母占一个字节
        InputStream inputStream = new ByteArrayInputStream(msg.getBytes());

        OutputStream outputStream = new ByteArrayOutputStream();

        int len;
        // len读取的是单个字节的Unicode值
        while ((len = inputStream.read())!=-1){
            // Character.toUpperCase(len)正好是将len所代表的Unicode值转换为大写
            outputStream.write(Character.toUpperCase(len));
        }

        System.out.println(outputStream);

        inputStream.close();
        outputStream.close();
    }
}
