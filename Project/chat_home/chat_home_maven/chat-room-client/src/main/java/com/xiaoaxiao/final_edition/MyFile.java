package com.xiaoaxiao.final_edition;

import java.io.Serializable;

/**
 * Created by xiaoaxiao on 2019/8/29
 * Description:
 */
public class MyFile implements Serializable {

    //序列化ID
    private static final long serialVersionUID = -5809782578272943999L;

    // 文件名
    private String file_name;
    // 文件字节数组
    private byte[] file_data;

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public byte[] getFile_data() {
        return file_data;
    }

    public void setFile_data(byte[] file_data) {
        this.file_data = new byte[file_data.length];
        for (int i = 0; i < file_data.length; i++) {
            this.file_data[i] = file_data[i];
        }

    }
}
