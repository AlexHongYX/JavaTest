package com.xiaoaxiao.test.io_test;

import java.io.File;
import java.io.IOException;

/**
 * Created by xiaoaxiao on 2019/7/25
 * Description: File文件创建
 */
public class FileTest1 {

    public static void main(String[] args) {
        File file = new File("C:"+File.separator+"Users"+File.separator+
                "xiaoaxiao"+File.separator+"Desktop"+File.separator+"Test.java");
        if(!file.exists()){
            try {
                boolean createSuccess = file.createNewFile();
                if (createSuccess){
                    System.out.println("新建成功");
                }else {
                    System.out.println("新建失败");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            boolean deleteSuccess = file.delete();
            if(deleteSuccess){
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        }

    }
}
