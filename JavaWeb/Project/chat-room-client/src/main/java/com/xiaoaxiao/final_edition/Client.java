package com.xiaoaxiao.final_edition;

/**
 * Created by xiaoaxiao on 2019/8/3
 * Description: 多线程版本客户端
 * 读线程+写线程（保证写的同时可以读）
 */

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

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

            while (true) {
                // 读取线程必须是读每一条数据都重新创建一个ObjectInputStream
                //TODO
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(this.client.getInputStream()));
                Object obj = ois.readObject();
                MyData myData = (MyData) obj;
//                System.out.println(myData.getFunction());
//                System.out.println(myData.getMessage());
//                System.out.println(myData.getMyFile());
                // 若发送的是普通消息，直接打印即可
                if(myData.getFunction().equals("SendMessage")){
                    System.out.println(myData.getMessage());
                }else if(myData.getFunction().equals("SendFile")){
                    // 若发送的是文件，除了打印信息外，还要将文件置入指定位置
                    System.out.println(myData.getMessage());
                    copy_file(myData.getMyFile());
                    System.out.println("接收文件"+myData.getMyFile().getFile_name()+"成功，请在F盘test目录下查看");
                }else if(myData.getFunction().equals("CloseSocket")){
                    System.out.println("客户端已关闭");
                    this.client.close();
                    break;
                }

//                // 若客户端退出，则此线程也应该退出
//                if (client.isClosed()) {
//                    // 若客户端已经关闭，则也向服务端发送该客户端被关闭的消息(与byebye相同)
//                    break;
//                }

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void copy_file(MyFile myFile){
        String fileName = myFile.getFile_name();
        File file = new File("F:"+File.separator+"test"+File.separator+fileName);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            OutputStream os = new FileOutputStream(file);
            os.write(myFile.getFile_data());
            os.close();
        } catch (IOException e) {
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
        // 使用对象流向服务端进行输出
        ObjectOutputStream oos = null;
        // 设定一个boolean值代表必须先进行注册
        boolean isRegisted = false;
        try {
            oos = new ObjectOutputStream(this.client.getOutputStream());

            while (true) {
                System.out.println("请输入要发送的信息..");
                String strToServer;
                MyData myData = new MyData();
                if (in.hasNext()) {
                    strToServer = in.nextLine();
                    // 必须要先注册再干其他的事情
                    if(strToServer.matches("userName:[a-zA-Z0-9]{1,10}")){
                        myData.setFunction("register");
                        myData.setMessage(strToServer.substring(9));
                        isRegisted = true;
                    }else{
                        // 只有当已经注册了，才可以干其他的事情(发文件)
                        if(isRegisted){
                            // 群聊
                            if(strToServer.matches("G:[\\u4e00-\\u9fa5\\w\\W]{0,100}")){
                                myData.setFunction("GroupChat");
                                myData.setMessage(strToServer.substring(2));
                            }
                            // 私聊
                            else if (strToServer.matches("P:[a-zA-Z0-9]{1,10}-[\\u4e00-\\u9fa5\\w\\W]{0,100}")){
                                // 将前面的"P:"直接删掉
                                String str = strToServer.substring(2);
                                // 此时变成了"用户名-私聊内容"，用户名肯定是第一个"-"前的内容
                                String userName = str.split("-")[0];
                                // 将第一个"-"以及前面的内容都删掉(用户名)
                                int index = str.indexOf("-");
                                str = str.substring(index+1);
                                String privateMsg = str;
                                myData.setFunction("PrivateChat");
                                myData.setMessage(privateMsg);
                                myData.setDestUser(userName);
                            }
                            // 如果输入的是文件发送相关
                            //  File$userName$filePath
                            else if(strToServer.startsWith("File")){
                                String filePath = strToServer.split("\\$")[2];
                                String userName = strToServer.split("\\$")[1];
                                int index = strToServer.lastIndexOf(File.separator);
                                String fileName = strToServer.substring(index+1);

                                myData.setFunction("SendFile");
                                myData.setDestUser(userName);
                                MyFile myFile = new MyFile();
                                myFile.setFile_name(fileName);

                                byte[] b = load_file(filePath);
                                // 如果b为null说明文件不存在/加载不成功
                                if(b == null){
                                    continue;
                                }
                                myFile.setFile_data(b);
                                myData.setMyFile(myFile);
                            }
                            // 如果输入的是结束语
                            // byebye
                            else if (strToServer.equals("byebye")){
                                myData.setFunction("byebye");
                            }
                            else {
                                myData.setFunction("wrong");
                            }
                        }else {
                            myData.setFunction("wrong");
                        }
                    }


                    // 必须先给服务器发送信息，再break
                    oos.writeObject(myData);
                    oos.flush();

                    if (strToServer.equals("byebye")){
                        in.close();
                        break;
                    }

//                    if (myData.getFunction().equals("byebye")) {
//                        System.out.println("客户端已关闭");
//
//                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据文件路径将文件保存为一个字节数组返回
     * @param filePath  文件路径
     * @return  保存字节流数据的字节数组
     */
    private byte[] load_file(String filePath){
        File file = new File(filePath);
        if (!file.exists()){
            System.out.println("文件不存在");
            return null;
        }
        try {
            InputStream is = new FileInputStream(file);
            byte[] b = new byte[(int) file.length()];
            is.read(b);
            is.close();
            return b;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

public class Client {

    public static void main(String[] args) {
        try {
            Socket client = new Socket("127.0.0.1", 6666);
            Thread writeThread = new Thread(new WriteToServerThread(client));
            Thread readThread = new Thread(new ReadFromServerThread(client));

            // 先打印提示信息
            String message = "******************************************************************************************************************\n" +
                    "                                               输入前请仔细阅读<帮助文档>\n"+
                    "       输入 userName:<username>：进行注册\n"+
                    "       输入 G:<groupMessage>：发送群聊信息(发送消息不超过100个字符<任意字符>且不能为空)\n" +
                    "       输入 P:<username>-<privateMessage>：给username发送私聊信息(发送消息不超过100个字符<任意字符>且不能为空)\n" +
                    "       输入 File$<username>$<FilePath>：给username发送一个文件，文件地址为FilePath\n"+
                    "       输入 byebye：退出程序\n" +
                    "******************************************************************************************************************\n";
            System.out.println(message);
            // 开启读线程与写线程
            writeThread.start();
            readThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
