package com.xiaoaxiao.test.io_test;

import java.io.File;
import java.io.Writer;
import java.util.Date;

/**
 * Created by xiaoaxiao on 2019/7/25
 * Description: 文件信息
 */
public class FileTest3 {

    public static void main(String[] args) {
//        File file = new File("C:"+File.separator+"Users"+File.separator+
//                "xiaoaxiao"+File.separator+"Desktop"+File.separator
//                +"Test.java");
//        if (file.exists() && file.isFile()){
//            System.out.println("文件大小："+file.length());
//            System.out.println("最后一次修改日期："+new Date(file.lastModified()));
//        }
        System.getProperties().list(System.out);
    }
}
