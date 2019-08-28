package com.xiaoaxiao.test;

/**
 * Created by xiaoaxiao on 2019/8/28
 * Description: 已成功发送并接收图片了
 */
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyClient {

    public static void main(String[] args) throws Exception {

        Socket socket = null;
        ObjectOutputStream os = null;
        ObjectInputStream is = null;

        try {
            socket = new Socket("localhost", 23333);

            // 使用输出流输出图片相关信息
            os = new ObjectOutputStream(socket.getOutputStream());
            ConnectionData con=new ConnectionData();
            con.setFunction("上传");
            con.setToken("be55ed7101044ce72eddc8e5362de9ba");
            con.setPhone("15271581490");
            con.setPassword("password");
            Picture tp=new Picture();
            tp.setPic_data(load_picture_from_file("E:\\test\\test5.jpg"));
            tp.setPic_name("test.jpg");
            List<Picture> conP=new ArrayList<>();
            conP.add(tp);
            con.setPicture_list(conP);

            os.writeObject(con);
            os.flush();

            // 使用输入流接收图片相关信息
            is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            Object obj = is.readObject();

            if (obj != null) {
                con=(ConnectionData)obj;
                System.out.println(con.getToken()+"\n"+con.getState()+"\n"+con.getWrong_info());
                System.out.println("收到的手机号变为："+con.getPhone());
                if(con.getPicture_list()!=null&&con.getPicture_list().size()!=0)
                {
                    System.out.println("成功接受到返回的图片");
                }
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        } finally {
            is.close();
            os.close();
            socket.close();
        }

    }

    /**
     * 根据图片地址 将图片以二进制的方式写入到对应位置
     * @param pic_url 图片地址
     * @return  返回该图片所对应的字节数组
     */
    private static byte[] load_picture_from_file(String pic_url)
    {
        try {
            File f = new File(pic_url);
            InputStream is = new FileInputStream(f);
            byte[] b = new byte[(int) f.length()];
            is.read(b);
            is.close();
            return b;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
