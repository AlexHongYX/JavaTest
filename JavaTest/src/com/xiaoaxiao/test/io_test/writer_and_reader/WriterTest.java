package com.xiaoaxiao.test.io_test.writer_and_reader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by xiaoaxiao on 2019/7/25
 * Description: Writer测试
 */
public class WriterTest {

    public static void main(String[] args) throws IOException {

        File file = new File("C:"+File.separator+"Users"+File.separator+
                "xiaoaxiao"+File.separator+"Desktop"+File.separator+"Test.java");

        String msg = "天天向上";

        Writer writer = new FileWriter(file);

        writer.write(msg);

        writer.close();
    }
}
