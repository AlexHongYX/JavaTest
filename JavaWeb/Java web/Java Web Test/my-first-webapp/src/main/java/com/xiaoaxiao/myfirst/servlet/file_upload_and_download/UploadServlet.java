package com.xiaoaxiao.myfirst.servlet.file_upload_and_download;

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
        // 使用javax.servlet.http.Part获取请求中指定name的文件
        Part part = req.getPart("file");

        // 获取文件夹(文件夹在当前服务器上的位置)路径
        // uploadPath：当前文件夹应该是在当前服务器的根目录下的upload文件夹
        String uploadPath = req.getServletContext().getRealPath("/upload");
        File uploadDirectory = new File(uploadPath);
        // 如果该文件夹不存在(首次访问)，则创建该文件夹
        if(!uploadDirectory.exists()){
            uploadDirectory.mkdirs();
        }

        // 获取文件全部名，文件名.文件类型
        String fileName = part.getSubmittedFileName();
        // 通过父路径(uploadDirectory)+子路径(fileName)，获取当前文件
        File uploadFile = new File(uploadDirectory,fileName);

        // 如果当前图片不存在，将当前图片放入服务器中
        // 若存在就直接跳过这一步即可
        if(!uploadFile.exists()){
            // 将Part中保存的文件信息，写入到对应文件(uploadFile)中
            InputStream is = part.getInputStream();
            FileOutputStream fos = new FileOutputStream(uploadFile);

            byte[] buff = new byte[1024];
            int len = -1;
            while ((len=is.read(buff))!=-1){
                fos.write(buff,0,len);
            }

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
        sb.append("upload/").append(fileName);
        sb.append("\" alt=\"无法显示\"/>\n" +
                "    <hr/>\n" +
                "    <a href=\"");
        sb.append("download?file=").append(fileName);
        sb.append("\">下载该图片</a>\n" +
                "    <hr/>\n" +
                "    <a href=\"listFiles\">查看所有图片</a>\n" +
                "</body>\n" +
                "</html>");
        resp.getWriter().println(sb.toString());
        resp.getWriter().flush();
    }
}
