package com.xiaoaxiao.test.io_test.writer_and_reader;

import java.io.*;

/**
 * Created by xiaoaxiao on 2019/7/25
 * Description: Reader测试
 */
public class ReaderTest {

    public static void main(String[] args) throws IOException {
        File file = new File("C:"+File.separator+"Users"+File.separator+
                "xiaoaxiao"+File.separator+"Desktop"+File.separator+"Test.java");

        if (file.exists()){
            Reader reader = new FileReader(file);

            char[] data = new char[1024];

            int ret = reader.read(data);

            System.out.println(ret);
            System.out.println(new String(data,0,ret));

            reader.close();
        }

    }
}
