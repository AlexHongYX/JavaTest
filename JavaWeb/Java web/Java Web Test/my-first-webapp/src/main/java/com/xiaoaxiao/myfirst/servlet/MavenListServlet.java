package com.xiaoaxiao.myfirst.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xiaoaxiao on 2019/8/28
 * Description: 列出自己的Maven仓库
 */
public class MavenListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取参数name的值(?name=xxx)
        String name = req.getParameter("name");
        String repository;
        // 如果name为null或空，说明没传name，因此需要访问根目录
        if(name==null||name.isEmpty()){
            repository = "D:"+ File.separator+"maven"+File.separator+"apache-maven-3.3.1-bin"
                    +File.separator+"apache-maven-3.3.1"+File.separator+"repository";
        }else{
            // 否则的话，需要访问根目录+name(中间使用File.separator连接)
            repository = "D:"+ File.separator+"maven"+File.separator+"apache-maven-3.3.1-bin"
                    +File.separator+"apache-maven-3.3.1"+File.separator+"repository"+File.separator+name.replace("_",File.separator);
        }

        // 指定发送的数据编码和内容类型
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        // 遍历指定目录，并将所有的内容放在files中
        File directory = new File(repository);
        File[] files = directory.listFiles();

        String lastName = "";
        if(name!=null){
            lastName = name.replace("_",File.separator);
            int index = lastName.lastIndexOf(File.separator);
            if(index>0){
                lastName = lastName.substring(0,index);
                lastName = lastName.replace(File.separator,"_");
            }else {
                lastName = "";
            }
        }


        // 使用sb构建前端框架
        StringBuilder sb = new StringBuilder();
        sb.append("<html>")
                .append("<head>")
                    .append("<title>")
                        .append("MavenList")
                    .append("</title>")
                .append("</head>")
                .append("<body>");
                    if(name!=null){
                        sb.append("<a href='").append("maven?name=").append(lastName).append("'>").append("..").append("</a>").append("<br/>");
                    }
                    if(files!=null){
                        for (File file : files){

                            // 获取文件名
                            String fileName = file.getName();
                            String nextName = (name==null||name.isEmpty())?fileName:name+"_"+fileName;
                            // 将文件名拼接成一个超链接
                            sb.append("<a href='").append("maven?name=").append(nextName).append("'>")
                                    .append(fileName).append("</a>").append("<br/>");
                        }
                    }else {
                        sb.append("暂无数据");
                    }
        sb.append("</body>")
                .append("</html>");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(sb.toString());
        printWriter.flush();
    }
}
