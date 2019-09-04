package com.xiaoaxiao.myfirst.servlet.FileUploadAndDownload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by xiaoaxiao on 2019/9/4
 * Description: 展示当前服务器中所存在的文件列表
 */
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String directoryPath = req.getServletContext().getRealPath("/upload");
        File directory = new File(directoryPath);

        File[] files = directory.listFiles();

        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>文件信息展示</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <table>\n" +
                "        <tr>\n" +
                "            <th>文件名</th>\n" +
                "            <th>文件大小</th>\n" +
                "            <th>下载</th>\n" +
                "        </tr>");
        if(files!=null){
            for (File file:files){
                sb.append("<tr>\n" +
                        "            <td>");
                sb.append(file.getName());
//                System.out.println(file.getName());
                sb.append("</td>\n" +
                        "            <td>");
                sb.append(file.length()/1024+"KB");
                sb.append("</td>\n" +
                        "            <td><a href=\"");
                sb.append("download?file=").append(file.getName());
                sb.append("\">点击下载该文件</a></td>\n" +
                        "        </tr>");
            }
        }
        sb.append("    </table>\n" +
                "</body>\n" +
                "</html>");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(sb.toString());
        resp.getWriter().flush();
    }
}
