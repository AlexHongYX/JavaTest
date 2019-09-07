package com.xiaoaxiao.myfirst.servlet.session_manage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoaxiao on 2019/9/5
 * Description: 省市区联动——通过URL的增减
 */
public class TokenServlet extends HttpServlet {
    private Map<String, List<String>> cityMap = new HashMap<>();
    private Map<String,List<String>> countryMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        List<String> shannxi = new ArrayList<>();
        shannxi.add("西安市");
        shannxi.add("宝鸡市");
        shannxi.add("铜川市");
        shannxi.add("咸阳市");
        cityMap.put("陕西省",shannxi);

        List<String> xian = new ArrayList<>();
        xian.add("临潼区");
        xian.add("长安区");
        xian.add("未央区");
        countryMap.put("西安市",xian);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        String pro = req.getParameter("pro");
        String city = req.getParameter("city");
        if(pro==null&&city==null){
            pw.write("没有数据");
        }else {
            if(pro==null){
                pw.write("pro不能为null");
            }else {
                StringBuilder sb = new StringBuilder();
                // 如果给了省没有给城市，显示该省中的所有市
                if(city==null){
                    List<String> citys = this.cityMap.get(pro);

                    if (citys==null){
                        sb.append("没有查询到");
                    }else {
                        for (String c : citys){
                            sb.append("<a href=\"token?");
                            sb.append("pro=").append(pro);
                            sb.append("&");
                            sb.append("city=");
                            sb.append(c);
                            sb.append("\">");
                            sb.append(pro).append(",").append(c);
                            sb.append("</a>").append("<br/>");
                        }
                    }
                }else {
                    // 若city不为null，则显示这个city所对应的城市中的区
                    List<String> countrys = this.countryMap.get(city);
                    if(countrys==null){
                        sb.append("没有查询到");
                    }else {
                        for (String c : countrys){
                            sb.append("<a href=\"token?");
                            sb.append("pro=").append(pro);
                            sb.append("&");
                            sb.append("city=");
                            sb.append(city);
                            sb.append("\">");
                            sb.append(pro).append(",").append(city).append(",").append(c);
                            sb.append("</a>").append("<br/>");
                        }
                    }
                }
                pw.println(sb.toString());
            }
        }
        pw.flush();
    }
}
