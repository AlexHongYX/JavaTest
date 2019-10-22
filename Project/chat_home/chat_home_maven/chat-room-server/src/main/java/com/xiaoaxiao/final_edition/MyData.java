package com.xiaoaxiao.final_edition;

import java.io.Serializable;

/**
 * Created by xiaoaxiao on 2019/8/29
 * Description: 将所发送的消息/文件/图片封装成一个类，使用对象流进行Socket的传输
 *              将文件/图片，这两种类似操作的再封装成一个类MyFile
 */
public class MyData implements Serializable {

    //序列化ID
    private static final long serialVersionUID = -5809782578272943999L;


    // 功能
    private String function = null;
    // 信息内容
    private String message = null;
    // 目标对象
    private String destUser = null;
    // 图片/文件信息
    private MyFile myFile = null;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDestUser() {
        return destUser;
    }

    public void setDestUser(String destUser) {
        this.destUser = destUser;
    }

    public MyFile getMyFile() {
        return myFile;
    }

    public void setMyFile(MyFile myFile) {
        this.myFile = myFile;
    }
}

