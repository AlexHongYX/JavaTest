package com.xiaoaxiao.multi;

import java.io.File;
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

    // 设置一个boolean值代表当前socket只能注册一个用户，再注册就提示错误
    private boolean isRegisted = false;

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
                    // userName：用户名     strFromClient.startsWith("userName:")&&
                    if (strFromClient.matches("userName:[a-zA-Z0-9]{1,10}")){
                        // 先将字符串分割，将用户名传入对应函数
                        String userName = strFromClient.split(":")[1];
                        // 注册前先判断该username是否已存在，若已存在则提示"该用户名已存在"
//                        if(hasTheOne(userName)){
//                            String message = "*********************************************\n" +
//                                    "**当前用户名已存在，请重新注册(输入 userName:<username>：进行注册) **\n"+
//                                    "*********************************************\n";
//                            sendMessage(this.socket,message,true);
//                        }else{
//                            // 如果当前名字不存在则正常注册即可
//                            registerUser(userName);
//                        }
                        registerUser(userName);
                        continue;
                    }else{
                        // 必须要先注册，只有注册成功了，在Map中能找到对应的username了
                        // 才能继续进行之后的操作，否则提示"请先注册"
                        String username = findUsername();
                        if(username==null){
                            String message = "*********************************************\n" +
                                    "**未注册或格式错误，输入 userName:<username>：进行注册(名字长度为1-10位字母与数字的组合<字母大小写均可>) **\n"+
                                    "*********************************************\n";
                            sendMessage(this.socket,message,true,false);
                        }else{
                            // 群聊流程
                            // G：群聊内容   strFromClient.startsWith("G:")
                            // 内容的正则可以是包括中文、标点、数字、字母在内的任意字符
                            if (strFromClient.matches("G:[\\u4e00-\\u9fa5\\w\\W]{0,100}")){
                                String groupMsg = strFromClient.split(":")[1];
                                groupChat(groupMsg);
                                continue;
                            }

                            // 私聊流程
                            // P：用户名-私聊内容   strFromClient.startsWith("P:")
                            else if (strFromClient.matches("P:[a-zA-z0-9]{1,10}-[\\u4e00-\\u9fa5\\w\\W]{0,100}")){
                                String userName = strFromClient.split(":")[1]
                                        .split("-")[0];
                                String privateMsg = strFromClient.split(":")[1]
                                        .split("-")[1];
                                privateChat(userName,privateMsg);
                                continue;
                            }

                            // 用户退出
                            // 用户名：byebye
                            else if (strFromClient.equals("byebye")){
                                byebye();
                                continue;
                            }
                            // 给某个用户发送文件
                            // File：用户名-文件内容字符串
                            else if(strFromClient.startsWith("File")){
                                String userName = strFromClient.split(":")[1]
                                        .split("-")[0];
                                String fileContent = strFromClient.split(":")[1]
                                        .split("-")[1];
                                sendFile(userName,fileContent);
                                continue;
                            }
                            // 如果输入的不是正确的消息，服务器端需要给客户端返回一条"帮助文档"
                            else{
                                String message = "*********************************************\n" +
                                        "**输入错误，请仔细阅读帮助文档**\n"+
                                        "**输入 G:<groupMessage>：发送群聊信息(发送消息不超过100个字符<任意字符>且不能为空)**\n" +
                                        "**输入 P:<username>-<privateMessage>：给username发送私聊信息(发送消息不超过100个字符<任意字符>且不能为空)**\n" +
                                        "**输入 byebye：退出程序**\n" +
                                        "*********************************************\n";
                                sendMessage(this.socket,message,true,false);
                            }
                        }

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 私发文件
     * @param userName  目标用户
     * @param fileContent 文件的二进制内容字符串
     */
    private void sendFile(String userName, String fileContent) {
        if(hasTheOne(userName)){
            // 判断一下是不是在自己给自己发文件——疯了
            if(userName.equals(findUsername())){
                sendMessage(this.socket,"你给自己发文件是疯了？",true,false);
            }else{
                // 当个人吧，给一个存在的人发文件(正常操作)
                Socket client = clientMap.get(userName);
                // 若发的是文件将isFile设置为true
                sendMessage(client,"给您发送了一个文件："+fileContent,false,true);
            }
        }else{
            sendMessage(this.socket,"该用户不存在!",true,false);
        }
    }

    /**
     * 在Map中查看有无该用户(根据username)
     * @param userName 该用户的username
     * @return
     */
    private boolean hasTheOne(String userName){
        if(clientMap.containsKey(userName)){
            return true;
        }
        return false;
    }

    /**
     * 根据当前socket(this.socket)在Map中寻找对应用户的username
     * @return  该用户的username
     */
    private String findUsername(){
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
        return username;
    }

    /**
     * 专门将发送消息封装为一个函数
     *  @param socket   目标对象的socket
     *  @param message   发送的内容
     *  @param isHidden   若为帮助文档或未注册或当前名字已存在，只显示message即可
     *  @param isFile   是否是在发送文件，若是在发送文件则"用户"前面再加上File，这样客户端看到是以File开头的就知道发送的是文件了
     */
    private void sendMessage(Socket socket,String message,boolean isHidden,boolean isFile){
        try {
            PrintStream printStream = new PrintStream(socket.getOutputStream()
                    ,true,"UTF-8");

            // 若isUserHelp为真则只需要打印message，否则还需要打印username
            if(isHidden){
                printStream.println(message);
            }else if(isFile){
                // 若发送的是文件，则在"用户"前多加一个File，让客户端就能判断出是否是文件了
                String username = findUsername();
                printStream.println("File用户"+username+message);
            }else{
                String username = findUsername();
                printStream.println("用户"+username+message);
            }
            // printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以userName为key注册当前用户
     * @param userName 客户端传来的username
     */
    private void registerUser(String userName){
        // 先判断当前用户名时候已经被注册了
        if(hasTheOne(userName)){
            String message = "*********************************************\n" +
                    "**当前用户名已存在，请重新注册(输入 userName:<username>：进行注册) **\n"+
                    "*********************************************\n";
            sendMessage(this.socket,message,true,false);
            return ;
        }
        if(this.isRegisted){
            String message = "*********************************************\n" +
                    "**当前用户已注册，不可重复注册，请输入其他命令**\n"+
                    "*********************************************\n";
            sendMessage(this.socket,message,true,false);
            return ;
        }
        System.out.println("用户"+userName+"上线啦");
        // 将用户信息保存在Map中
        // 每个socket都对应的是一个线程
        clientMap.put(userName,this.socket);
        // 将isRegisted设置为true，说明当前socket已经注册了一个用户了，不能再注册其他用户
        isRegisted = true;
        System.out.println("当前群聊人数为："+clientMap.size()+"人");
        // 添加成功后，服务器端向客户端返回信息
        sendMessage(this.socket,"注册成功",false,false);
    }

    /**
     * 发送一条群聊信息
     * @param groupMsg  具体的群聊信息
     */
    private void groupChat(String groupMsg){
        Set<Map.Entry<String,Socket>> entrySet =
                clientMap.entrySet();
        Iterator<Map.Entry<String,Socket>> iterator =
                entrySet.iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Socket> entry = iterator.next();
            Socket client = entry.getValue();
//            try {
//                PrintStream printStream = new PrintStream(client.getOutputStream()
//                    ,true,"UTF-8");
//                printStream.println("群聊信息："+groupMsg);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            if(client.equals(this.socket)){
                continue;
            }
            sendMessage(client,"发送了一条群聊消息："+groupMsg,false,false);
        }
    }

    /**
     * 发送一条私聊信息
     * @param userName  具体的目标私聊对象
     * @param privateMsg    具体的私聊信息
     */
    private void privateChat(String userName,String privateMsg){
        // 先判断目标用户是否存在，若存在则发送
        // 若不存在则给当前用户提示"该用户不存在"
        if(hasTheOne(userName)){
            // 判断一下是不是在自己给自己发消息——疯了
            if(userName.equals(findUsername())){
                sendMessage(this.socket,"你给自己发消息是疯了？",true,false);
            }else{
                // 当个人吧，给一个存在的人发消息(正常操作)
                Socket client = clientMap.get(userName);
                sendMessage(client,"给您发送了一条私聊消息："+privateMsg,false,false);
            }
        }else{
            sendMessage(this.socket,"该用户不存在!",true,false);
        }

    }

    /**
     * 用户退出
     */
    private void byebye(){
        // 由于不知道具体是哪个用户说的byebye，因此需要
        // 通过Socket来找到对应的userName，并将此对象从
        // Map中删除
        String username = findUsername();
        clientMap.remove(username);
        System.out.println("用户"+username+"已下线");
        System.out.println("当前群聊人数为："+clientMap.size()+"人");
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
