package com.xiaoaxiao.test.io_test.input_and_output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by xiaoaxiao on 2019/7/25
 * Description: OutputStream测试
 */
public class OutputStreamTest {

    public static void main(String[] args) throws Exception {
        File file = new File("C:"+File.separator+"Users"+File.separator+
                "xiaoaxiao"+File.separator+"Desktop"+File.separator+"Test.java");
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }

        OutputStream outputStream = new FileOutputStream(file);

        String msg = "比特科技";

        // 将String类型变为byte数组传入输出流
        outputStream.write(msg.getBytes());

        outputStream.close();

    }
}
