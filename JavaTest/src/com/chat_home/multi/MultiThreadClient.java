package com.xiaoaxiao.chat_home.multi;

/**
 * Created by xiaoaxiao on 2019/8/3
 * Description: 多线程版本客户端
 *          读线程+写线程（保证写的同时可以读）
 */

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 读取服务器信息线程
 */
class ReadFromServerThread implements Runnable{

    // 无论是读线程还是写线程都需要同一个Socket
    // 通过构造方法将主函数中的socket传入
    private Socket client;

    public ReadFromServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(client.getInputStream());
            while (true){
                if (scanner.hasNext()){
                    System.out.println("从服务器端接收信息："+scanner.nextLine());
                }
                // 若客户端退出，则此线程也应该退出
                if (client.isClosed()){
                    System.out.println("客户端已关闭");
                    break;
                }
            }
            Thread.sleep(500);
            // 关闭流
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 将信息发送给服务器线程
 */
class WriteToServerThread implements Runnable{

    private Socket client;

    public WriteToServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        // 获取键盘输入
        Scanner in = new Scanner(System.in);
        // 获取客户端输出流
        PrintStream printStream = null;
        try {
            printStream = new PrintStream(client.getOutputStream()
                ,true,"UTF-8");

            while (true){
                System.out.println("请输入要发送的信息..");
                String strToServer;
                if (in.hasNext()){
                    strToServer = in.nextLine();
                    printStream.println(strToServer);
                    if (strToServer.equals("byebye")){
                        System.out.println("关闭客户端");
                        in.close();
                        printStream.close();
                        client.close();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

public class MultiThreadClient {

    public static void main(String[] args) {
        try {
            Socket client = new Socket("127.0.0.1",6666);
            Thread writeThread = new Thread(new WriteToServerThread(client));
            Thread readThread = new Thread(new ReadFromServerThread(client));

            // 开启读线程与写线程
            writeThread.start();
            readThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
