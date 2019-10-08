package com.xiaoaxiao.test;

/**
 * Created by xiaoaxiao on 2019/8/28
 * Description:
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
  * Created by 32706 on 2017/3/12.
  * 该类定义数据传送类
  */
public class ConnectionData implements java.io.Serializable{
    private String function=null;//功能字段
    private String phone=null;//用户手机号
    private String password=null;//用户密码
    private String token=null;//token数据
    private String state="successful";
    private String wrong_info=null;
    private List<Picture> picture_list=null;//传输的图片集合

    /**设置各个参数**/
    public void setFunction(String fun){
        this.function=fun;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setToken(String tk){
        this.token=tk;
    }
    public void setPicture_list(List<Picture> in){
        if(this.picture_list==null) {
            picture_list=new ArrayList<>();
        }
        this.picture_list.addAll(in);
    }
    public void setState(String in){
        this.state=in;
    }
    public void setWrong_info(String info){
        if(this.wrong_info==null){
            this.wrong_info=info;
        }else {
            this.wrong_info+=info;
        }
    }

    /**取出各个参数**/
    public String getFunction() {
        return function;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public String getState() {
        return state;
    }

    public String getWrong_info() {
        return wrong_info;
    }

    public List<Picture> getPicture_list() {
        return picture_list;
    }
}

/**
 * 图片类
 */
class Picture implements Serializable{
        private String picture_no;
        private String pic_name;
        private long pic_size;
        private byte[] pic_data;

        public void setPicture_no(String picture_no) {
            this.picture_no = picture_no;
        }

        public void setPic_name(String pic_name) {
            this.pic_name = pic_name;
        }

        public void setPic_size(long pic_size) {
            this.pic_size = pic_size;
        }
        public void setPic_data(byte[] pic_data) {
            this.pic_data=new byte[pic_data.length];
            for(int i=0;i<pic_data.length;i++){
                this.pic_data[i]=pic_data[i];
            }
        }

        public String getPicture_no() {
            return picture_no;
        }

        public String getPic_name() {
            return pic_name;
        }

        public long getPic_size() {
            return pic_size;
        }

        public byte[] getPic_data() {
            return pic_data;
        }
}