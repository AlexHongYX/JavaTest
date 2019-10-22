package com.xiaoaxiao.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xiaoaxiao on 2019/8/28
 * Description:
 */
public class MyServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(23333);
        while (true) {
            Socket socket = server.accept();
            invoke(socket);
        }
    }


    private static void invoke(final Socket socket) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                ObjectInputStream is = null;
                ObjectOutputStream os = null;
                try {
                    is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                    os = new ObjectOutputStream(socket.getOutputStream());

                    Object obj = is.readObject();
                    ConnectionData con=(ConnectionData)obj;
                    System.out.println("收到的手机号: " + con.getPhone());
                    System.out.println("收到的图片张数为："+((con.getPicture_list()==null)?0:(con.getPicture_list().size())));
                    con.setPhone("1");

                    File file = new File("F://test//test.jpg");
                    OutputStream outputStream = new FileOutputStream(file);
                    Picture picture = con.getPicture_list().get(0);
                    outputStream.write(picture.getPic_data());
                    outputStream.close();

                    os.writeObject(con);
                    os.flush();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
