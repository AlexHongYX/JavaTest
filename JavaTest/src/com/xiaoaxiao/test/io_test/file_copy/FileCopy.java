package com.xiaoaxiao.test.io_test.file_copy;

import java.io.*;

/**
 * Created by xiaoaxiao on 2019/7/25
 * Description: 文件拷贝
 *                  1、一个字节一个字节拷贝（太慢）
 *                  2、添加一个缓冲区（较快）
 */
public class FileCopy {

    public static void main(String[] args) throws IOException {
        String sourcePath = "C:"+File.separator+"Users"+File.separator+
                "xiaoaxiao"+File.separator+"Desktop"+File.separator
                +"胖虎.jpg";
        String destPath = "C:"+File.separator+"Users"+File.separator+
                "xiaoaxiao"+File.separator+"Desktop"+File.separator+"傻瓜.jpg";

        copyFile(sourcePath,destPath);
    }



    private static void copyFile(String sourcePath,String destPath) throws IOException{

        File sourceFile = new File(sourcePath);
        File destFile = new File(destPath);

        InputStream inputStream = new FileInputStream(sourceFile);
        OutputStream outputStream = new FileOutputStream(destFile);

        long start = System.currentTimeMillis();
        int len;

//        // 每次只输入一个字节，再输出一个字节
//        while ((len = inputStream.read())!=-1){
//            outputStream.write(len);
//        }
        // 大小为1K字节的缓冲区
        byte[] data = new byte[1024];
        while ((len=inputStream.read(data))!=-1){
            outputStream.write(data,0,len);
        }

        long end = System.currentTimeMillis();
        System.out.println("文件拷贝完成，共耗时："+(end-start)+"ms");

        inputStream.close();
        outputStream.close();
    }
}
