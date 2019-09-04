package com.xiaoaxiao.myfirst.servlet.FileUploadAndDownload;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by xiaoaxiao on 2019/9/4
 * Description: 文件的展示与下载
 */
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // 获取要下载的文件名
        String fileName = req.getParameter("file");
//        System.out.println(fileName);
        String path = req.getServletContext().getRealPath("/upload");

        File downloadFile = new File(path,fileName);

        FileInputStream fis = new FileInputStream(downloadFile);

        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-Disposition", "attachment; filename="+fileName);

        ServletOutputStream fos = resp.getOutputStream();

        byte[] buff = new byte[1024];
        int len = -1;
        while ((len = fis.read(buff))!=-1){
            fos.write(buff,0,len);
        }



    }
}
