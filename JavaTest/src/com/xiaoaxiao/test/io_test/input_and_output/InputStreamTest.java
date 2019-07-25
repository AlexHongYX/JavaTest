package com.xiaoaxiao.test.io_test.input_and_output;

import java.io.*;

/**
 * Created by xiaoaxiao on 2019/7/25
 * Description: InputStream测试
 */
public class InputStreamTest {

    public static void main(String[] args) throws IOException {

        // 1、取得File对象
        File file = new File("C:"+File.separator+"Users"+File.separator+
                "xiaoaxiao"+File.separator+"Desktop"+File.separator+"Test.java");

        // 2、取得输入流
        InputStream inputStream = new FileInputStream(file);

        // 3、读取文件内容
        // 设置缓冲区
        byte[] data = new byte[1024];

        int len = inputStream.read(data);

        System.out.println(len);
        System.out.println(new String(data,0,len));

        // 关闭流
        inputStream.close();
    }
}
