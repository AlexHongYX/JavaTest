package com.xiaoaxiao.chat_home.single;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/8/3
 * Description: 单线程服务器端
 */
public class SingleThreadServer {
    public static void main(String[] args) throws IOException {
        // 1、创建基站
        ServerSocket serverSocket = new ServerSocket(6666);
        // 2、创建Socket（套接字），等待客户端连接
        System.out.println("等待客户端连接...");
        Socket socket = serverSocket.accept();
        // 3、建立连接后，进行数据的输入输出
        // 服务器端向客户端输出
        PrintStream printStream = new PrintStream(socket.getOutputStream()
            ,true,"UTF-8");
        printStream.println("Hi,I am Server");
        // 服务器端接收客户端的输入
        Scanner scanner = new Scanner(socket.getInputStream());
        if (scanner.hasNext()){
            System.out.println("客户端发来的信息为："+scanner.nextLine());
        }
        // 4、关闭流
        scanner.close();
        printStream.close();
        serverSocket.close();
    }
}
