package com.xiaoaxiao.multi;

/**
 * Created by xiaoaxiao on 2019/8/3
 * Description: 多线程版本客户端
 * 读线程+写线程（保证写的同时可以读）
 */

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 发送文件
 */
class SendFileThread implements Runnable{

    private Socket socket;
    private String filePath;
    private String userName;
    private String endString;

    public SendFileThread(Socket socket,String userName,String filePath) {
        this.socket = socket;
        this.filePath = filePath;
        this.userName = userName;
        this.endString = "File:"+userName+"-";
    }

    @Override
    public void run() {
        // 先判断传入的filePath是否合法(有没有这个文件)
        File file = new File(this.filePath);
        // 若当前文件不存在则//TODO
        if(!file.exists()){
            System.out.println("文件不存在");
        }else{
//            try {
//                InputStream inputStream = new FileInputStream(file);
//
//                int size = inputStream.available();
//                byte[] buffer = new byte[size];
//                inputStream.read(buffer);
//
//                this.endString+=new String(buffer,"GB2312");
//                // 将Windows下的默认换行\r\n中的\r转换为空字符串
//                Pattern pattern = Pattern.compile("\n");
//                Matcher matcher = pattern.matcher(this.endString);
//                this.endString = matcher.replaceAll("");
////                this.endString = this.endString.replaceFirst("[\n\r]+$", "");
////                System.out.println(this.endString);
//                inputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            // 取得输入流
            try {
                InputStream inputStream = new FileInputStream(file);

                // 读取文件内容
                // 设置缓冲区1k

                int len = inputStream.available();
                byte[] data = new byte[len];
                inputStream.read(data);
                this.endString += new String(data,"GB2312");
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public String getNewStr() {
        return this.endString;
    }
}

/**
 * 接收文件
 */
class AcceptFileThread implements Runnable{

    private String fileContent;

    public AcceptFileThread(String fileContent) {
        this.fileContent = fileContent;
    }

    /**
     * 向目标文件中写入数据
     */
    @Override
    public void run() {
        File file = new File("F:"+File.separator+"test"+File.separator+"hehe.txt");
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(this.fileContent.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 读取服务器信息线程
 */
class ReadFromServerThread implements Runnable {

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
            while (true) {
                if (scanner.hasNext()) {
                    String str = scanner.nextLine();
                    // 若以File开头说明发送的是文件，调用接收文件线程
                    if(str.startsWith("File")){
                        // 先将前面的File去掉
                        str = str.substring(4);
//                        System.out.println(str);
                        // 获取第一个冒号(:)的位置
                        // 这个冒号是一个中文字符
                        int index = str.indexOf("：");
                        System.out.println(str.substring(0,index));
                        // 第一个冒号后的所有内容即为文件的内容
                        String fileContent = str.substring(index+1);
                        // 开启接收线程进行文件的接收
                        AcceptFileThread acceptFileThread = new AcceptFileThread(fileContent);
                        Thread thread = new Thread(acceptFileThread);
                        thread.start();
                        // 当前线程等待 接收线程接收完成后打印接收成功
                        thread.join();
                        System.out.println("接收成功，请见F盘test文件中的hehe.txt");
                    }else{
                        // 若不以File开头说明是普通的消息
                        System.out.println(str);
                    }

                }
                // 若客户端退出，则此线程也应该退出
                if (client.isClosed()) {
//                    // 若客户端已经关闭，则也向服务端发送该客户端被关闭的消息(与byebye相同)
//                    PrintStream printStream = new PrintStream(client.getOutputStream(),
//                            true,"UTF-8");
//                    printStream.println("byebye");
//                    printStream.close();
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
class WriteToServerThread implements Runnable {

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
                    , true, "UTF-8");

            while (true) {
                System.out.println("请输入要发送的信息..");
                String strToServer;
                if (in.hasNext()) {
                    strToServer = in.nextLine();
                    // 如果输入的是文件发送相关
                    /**
                     *  File$userName$filePath
                    */

                    // 将输入的filePath替换成该文件路径对应的文件的字符串传给服务端
                    if(strToServer.startsWith("File")){
                        String filePath = strToServer.split("\\$")[2];
                        System.out.println(filePath);
                        String userName = strToServer.split("\\$")[1];
                        System.out.println(userName);
                        SendFileThread sendFileThread = new SendFileThread(this.client,userName,filePath);
                        Thread thread = new Thread(sendFileThread);
                        thread.start();
                        thread.join();
                        strToServer = sendFileThread.getNewStr();
                        System.out.println(strToServer);
                    }
                    // 必须先给服务器发送信息，再break
                    printStream.println(strToServer);
                    if (strToServer.equals("byebye")) {
                        System.out.println("客户端已关闭");
                        in.close();
                        printStream.close();
                        // 将client关闭，同时将读线程也关了与读线程的isClose相呼应
                        client.close();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

public class MultiThreadClient {

    public static void main(String[] args) {
        try {
            Socket client = new Socket("127.0.0.1", 6666);
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
