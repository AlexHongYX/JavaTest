package com.xiaoaxiao.chat_home.single;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/8/3
 * Description: 单线程客户端
 */
public class SingleThreadClient {

    public static void main(String[] args) throws IOException {
        // 1、建立Socket连接
        Socket socket = new Socket("127.0.0.1",6666);
        // 2、建立连接后，进行数据的输入输出
        Scanner scanner = new Scanner(socket.getInputStream());
        if (scanner.hasNext()){
            System.out.println("从服务器发来的信息为："+scanner.nextLine());
        }
        PrintStream printStream = new PrintStream(socket.getOutputStream()
            ,true,"UTF-8");
        printStream.println("Hello,I am Client");
        // 3、关闭流
        scanner.close();
        printStream.close();
        socket.close();
    }
}
