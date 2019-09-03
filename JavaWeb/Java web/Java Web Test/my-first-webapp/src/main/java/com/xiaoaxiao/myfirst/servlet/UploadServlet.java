package com.xiaoaxiao.myfirst.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xiaoaxiao on 2019/9/3
 * Description: 文件上传，上传页面在webapp中的upload.html
 */
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // file => 二进制流
        // 获得对应name的文件
        Part part = req.getPart("file");

        // 获取文件夹路径
        String uploadPath = req.getServletContext().getRealPath("/upload");
        File uploadDirectory = new File(uploadPath);
        if(!uploadDirectory.exists()){
            uploadDirectory.mkdirs();
        }

        // 获取文件全部名，文件名.文件类型
        String fileName = part.getSubmittedFileName();
        File uploadFile = new File(uploadDirectory,fileName);

        InputStream is = part.getInputStream();
        FileOutputStream fos = new FileOutputStream(uploadFile);

        byte[] buff = new byte[1024];
        int len = -1;
        while ((len=is.read(buff))!=-1){
            fos.write(buff,0,len);
        }

        // 在网页中展示图片
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>图片展示</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>显示已上传的图片</h1>\n" +
                "    <img src=\"");
        sb.append("/upload/").append(fileName);
        sb.append("\" alt=\"无法显示\"/>\n" +
                "</body>\n" +
                "</html>");
        resp.getWriter().println(sb.toString());
        resp.getWriter().flush();
    }
}
