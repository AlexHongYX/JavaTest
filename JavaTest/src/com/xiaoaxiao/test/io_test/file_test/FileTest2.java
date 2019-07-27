package com.xiaoaxiao.test.io_test.file_test;

import java.io.File;
import java.io.IOException;

/**
 * Created by xiaoaxiao on 2019/7/25
 * Description: 文件多级父目录创建
 */
public class FileTest2 {

    public static void main(String[] args) {
        File file = new File("C:"+File.separator+"Users"+File.separator+
                "xiaoaxiao"+File.separator+"Desktop"+File.separator+"com"
                +File.separator+"xiaoaxiao"+File.separator+"io"
                +File.separator+"IOTest.java");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if(file.exists()){
            file.delete();
        }else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
