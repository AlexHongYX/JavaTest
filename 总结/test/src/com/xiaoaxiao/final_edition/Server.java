package com.xiaoaxiao.final_edition;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            while (true){
                Object obj = ois.readObject();
                // 接收传入的对象
                MyData myData = (MyData)obj;

//
////                输出测试
//                System.out.println(myData.getFunction());
//                System.out.println(myData.getMessage());
//                System.out.println(myData.getDestUser());
//                System.out.println(myData.getMyFile());
//                if (myData.getMyFile()!=null){
//                    System.out.println(myData.getMyFile().getFile_name());
//                }

                // 注册流程
                if(myData.getFunction().equals("register")){
                    String userName = myData.getMessage();
                    // 若该用户名已存在
                    if(hasTheOne(userName)){
                        String message = "*********************************************\n" +
                                "**当前用户名已存在，请重新注册(输入 userName:<username>：进行注册) **\n"+
                                "*********************************************\n";
                        sendMessage(this.socket,message,null);
                    }else {
                        registerUser(userName);
                    }
                    continue;
                }else {
                    // 要先注册，只有注册成功了，在Map中能找到对应的username了
                    // 才能继续进行之后的操作，否则提示"请先注册"
                    String username = findUsername();
                    if(username==null){
                        String message = "*********************************************\n" +
                                "**未注册或格式错误，请先注册，输入 userName:<username>：进行注册(名字长度为1-10位字母与数字的组合<字母大小写均可>) **\n"+
                                "*********************************************\n";
                        sendMessage(this.socket,message,null);
                    }else{
                        // 群聊流程
                        // G：群聊内容
                        if (myData.getFunction().equals("GroupChat")){
                            String message = myData.getMessage();
                            groupChat(message);
                            continue;
                        }

                        // 私聊流程
                        // P：用户名-私聊内容
                        else if (myData.getFunction().equals("PrivateChat")){
                            String userName = myData.getDestUser();
                            String privateMsg = myData.getMessage();
                            privateChat(userName,privateMsg);
                            continue;
                        }

                        // 用户退出
                        // 用户名：byebye
                        else if (myData.getFunction().equals("byebye")){
                            byebye();
                            // 将输出流关闭掉
                            sendMessage(this.socket,"byebye",null);
                            break;
                        }

                        // 发送文件
                        // File$userName$filePath
                        else if (myData.getFunction().equals("SendFile")){
                            String userName = myData.getDestUser();
                            MyFile myFile = new MyFile();
                            myFile.setFile_data(myData.getMyFile().getFile_data());
                            myFile.setFile_name(myData.getMyFile().getFile_name());
                            sendFile(userName,myFile);
                            continue;
                        }

                        // 其他错误命令
                        else {
                            String message = "*********************************************\n" +
                                    "**输入错误，请仔细阅读帮助文档**\n"+
                                    "**输入 G:<groupMessage>：发送群聊信息(发送消息不超过100个字符<任意字符>且不能为空)**\n" +
                                    "**输入 P:<username>-<privateMessage>：给username发送私聊信息(发送消息不超过100个字符<任意字符>且不能为空)**\n" +
                                    "**输入 byebye：退出程序**\n" +
                                    "*********************************************\n";
                            sendMessage(this.socket,message,null);
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 私发文件
     * @param userName  目标用户
//     * @param fileContent 文件的二进制内容字符串
     */
    private void sendFile(String userName, MyFile myFile) {
        if(hasTheOne(userName)){
            // 判断一下是不是在自己给自己发文件——疯了
            if(userName.equals(findUsername())){
                sendMessage(this.socket,"你给自己发文件是疯了？",null);
            }else{
                // 当个人吧，给一个存在的人发文件(正常操作)
                Socket client = clientMap.get(userName);
                // 若发的是文件
                sendMessage(client,findUsername()+"给您发送了一个文件："+myFile.getFile_name(),myFile);
            }
        }else{
            sendMessage(this.socket,"该用户不存在!",null);
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
     *  @param client   目标对象的socket
     *  @param message   发送的内容
//     *  @param isHidden   若为帮助文档或未注册或当前名字已存在，只显示message即可
//     *  @param isFile   是否是在发送文件，若是在发送文件则"用户"前面再加上File，这样客户端看到是以File开头的就知道发送的是文件了
     */
    private void sendMessage(Socket client,String message,MyFile file){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

            // 设置返回给客户端的对象
            MyData returnData = new MyData();

            returnData.setMessage(message);
            if(file==null){
                if(message.equals("byebye")){
                    returnData.setFunction("CloseSocket");
                }else{
                    returnData.setFunction("SendMessage");
                }
            }else {
                returnData.setFunction("SendFile");
                returnData.setMyFile(file);
            }

            oos.writeObject(returnData);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以userName为key注册当前用户
     * @param userName 客户端传来的username
     */
    private void registerUser(String userName){
        if(this.isRegisted){
            String message = "*********************************************\n" +
                    "**当前用户已注册，不可重复注册，请输入其他命令**\n"+
                    "*********************************************\n";
            sendMessage(this.socket,message,null);
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
        sendMessage(this.socket,"注册成功",null);
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
            sendMessage(client,findUsername()+"发送了一条群聊消息："+groupMsg,null);
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
                sendMessage(this.socket,"你给自己发消息是疯了？",null);
            }else{
                // 当个人吧，给一个存在的人发消息(正常操作)
                Socket client = clientMap.get(userName);
                sendMessage(client,findUsername()+"给您发送了一条私聊消息："+privateMsg,null);
            }
        }else{
            sendMessage(this.socket,"该用户不存在!",null);
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

public class Server {
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
