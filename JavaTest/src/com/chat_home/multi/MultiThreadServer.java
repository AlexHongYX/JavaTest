package com.xiaoaxiao.chat_home.multi;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiaoaxiao on 2019/8/3
 * Description: 多线程版本服务器端
 */

// 使用线程实现服务端和客户端通信的业务处理
class ExecuteClient implements Runnable{

    // 所有线程共享
    // 维护所有的连接到服务端的客户端对象
    // ConcurrentHashMap是线程安全的，HashMap是线程不安全的所以不能用
    private static Map<String, Socket> clientMap = new
            ConcurrentHashMap<>();

    private Socket socket;

    public ExecuteClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            String strFromClient;
            while (true){
                if (scanner.hasNext()){
                    strFromClient = scanner.nextLine();
                    // 将Windows下的默认换行\r\n中的\r转换为空字符串
                    Pattern pattern = Pattern.compile("\r");
                    Matcher matcher = pattern.matcher(strFromClient);
                    strFromClient = matcher.replaceAll("");

                    // 注册流程
                    // userName：用户名
                    if (strFromClient.startsWith("userName")){
                        // 先将字符串分割，将用户名传入对应函数
                        String userName = strFromClient.split(":")[1];
                        registerUser(userName);
                        continue;
                    }
                    // 群聊流程
                    // G：群聊内容
                    if (strFromClient.startsWith("G")){
                        String groupMsg = strFromClient.split(":")[1];
                        groupChat(groupMsg);
                        continue;
                    }

                    // 私聊流程
                    // P：用户名-私聊内容
                    if (strFromClient.startsWith("P")){
                        String userName = strFromClient.split(":")[1]
                                        .split("-")[0];
                        String privateMsg = strFromClient.split(":")[1]
                                        .split("-")[1];
                        privateChat(userName,privateMsg);
                        continue;
                    }

                    // 用户退出
                    // 用户名：byebye
                    if (strFromClient.contains("byebye")){
                        // 由于不知道具体是哪个用户说的byebye，因此需要
                        // 通过Socket来找到对应的userName，并将此对象从
                        // Map中删除
                        String username = null;
                        Set<Map.Entry<String,Socket>> entrySet =
                                clientMap.entrySet();
                        Iterator<Map.Entry<String,Socket>> iterator =
                                entrySet.iterator();
                        while (iterator.hasNext()){
                            Map.Entry<String,Socket> entry =
                                    iterator.next();
                            if (entry.getValue().equals(this.socket)){
                                username = entry.getKey();
                                break;
                            }
                        }
                        clientMap.remove(username);
                        System.out.println("用户"+username+"已下线");
                        System.out.println("当前群聊人数为："+clientMap.size()+"人");
                        continue;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 注册以userName为key注册当前用户
    private void registerUser(String userName){
        System.out.println("用户"+userName+"上线啦");
        // 将用户信息保存在Map中
        // 每个socket都对应的是一个线程
        clientMap.put(userName,this.socket);
        System.out.println("当前群聊人数为："+clientMap.size()+"人");
        // 添加成功后，服务器端向客户端返回信息
        try {
            PrintStream printStream = new PrintStream(socket.getOutputStream()
                ,true,"UTF-8");
            printStream.println("用户注册成功");
            // printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 群聊
    private void groupChat(String groupMsg){
        Set<Map.Entry<String,Socket>> entrySet =
                clientMap.entrySet();
        Iterator<Map.Entry<String,Socket>> iterator =
                entrySet.iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Socket> entry = iterator.next();
            Socket socket = entry.getValue();
            try {
                PrintStream printStream = new PrintStream(socket.getOutputStream()
                    ,true,"UTF-8");
                printStream.println("群聊信息："+groupMsg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 私聊
    private void privateChat(String userName,String privateMsg){
        Socket socket = clientMap.get(userName);
        try {
            PrintStream printStream = new PrintStream(socket.getOutputStream()
                ,true,"UTF-8");
            printStream.println("私聊信息为"+privateMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class MultiThreadServer {
    public static void main(String[] args) {
        // 使用线程池接收多个客户端的连接请求
        ExecutorService executorService =
                    Executors.newFixedThreadPool(20);
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            for (int i = 0; i < 20; i++) {
                System.out.println("等待客户端连接");
                Socket socket = serverSocket.accept();
                System.out.println("有新的客户端连接，端口号为："
                        +socket.getPort());
                executorService.submit(new ExecuteClient(socket));
            }
            executorService.shutdown();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
