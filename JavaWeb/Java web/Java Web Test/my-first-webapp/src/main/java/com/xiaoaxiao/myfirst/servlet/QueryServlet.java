package com.xiaoaxiao.myfirst.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoaxiao on 2019/9/3
 * Description:
 *      功能：景点查询
 *      接口：/QueryServlet?city=<参数>
 *      分析：
 *          1、如果city参数没有传递，返回所有景点
 *          2、如果city参数有传递且已存在，返回该城市对应景点
 *          3、如果city参数有传递但不存在，什么也不返回
 *      效果：
 *          编号          所在城市        景点名称
 *          1               西安          大雁塔
 *          2               宝鸡          太白山
 *          ...             ...            ...
 */
// 将城市中文名称与城市景点封装成一个类
class ScenicSpotDto{
    private String city;
    private String spot;

    public ScenicSpotDto(String city, String spot) {
        this.city = city;
        this.spot = spot;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }
}

public class QueryServlet extends HttpServlet {

    // key:xian
    // value:西安
    private Map<String,String> cityMap = new HashMap<>();
    private Map<String, List<String>> scenicSpot = new HashMap<>();

    // key:xian
    // value:[大雁塔,钟楼...]

    /**
     * 初始化，将所有的城市与景点信息保存在各自的Map中
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        List<String> XiAn = new ArrayList<>();
        XiAn.add("大雁塔");
        XiAn.add("钟楼");
        XiAn.add("华清池");
        XiAn.add("兵马俑");
        this.cityMap.put("XiAn","西安");
        this.scenicSpot.put("XiAn",XiAn);

        List<String> BaoJi = new ArrayList<>();
        BaoJi.add("太白山");
        BaoJi.add("法门寺");
        BaoJi.add("关山牧场");
        this.cityMap.put("BaoJi","宝鸡");
        this.scenicSpot.put("BaoJi",BaoJi);

        List<String> XianYang = new ArrayList<>();
        XianYang.add("乾陵");
        XianYang.add("袁家村");
        this.cityMap.put("XianYang","咸阳");
        this.scenicSpot.put("XianYang",XianYang);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String city = req.getParameter("city");

        List<ScenicSpotDto> list = new ArrayList<>();
        // 如果city为null或为空，显示所有城市的信息
        if (city==null||city.isEmpty()){
            for (Map.Entry<String,List<String>> entry : this.scenicSpot.entrySet()){
                String cityPinYin = entry.getKey();
                List<String> spots = entry.getValue();
                String cityName = this.cityMap.get(cityPinYin);
                for (String spot:spots){
                    list.add(new ScenicSpotDto(cityName,spot));
                }
            }
        }else {
            List<String> spots = this.scenicSpot.get(city);
            // 若spots不为null才显示，为null就跳过
            if(spots!=null){
                String cityName = this.cityMap.get(city);
                for (String spot:spots){
                    list.add(new ScenicSpotDto(cityName,spot));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>城市景点</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <table>\n" +
                "        <thead>\n" +
                "            <tr>\n" +
                "                <th>编号</th>\n" +
                "                <th>城市</th>\n" +
                "                <th>景点</th>\n" +
                "            </tr>\n" +
                "        </thead>\n" +
                "        <tbody>");
        int id = 0;
        for (ScenicSpotDto ssd : list){
            // 从1开始计数
            id++;
            sb.append("<tr>")
                    .append("<td>").append(id).append("</td>")
                    .append("<td>").append(ssd.getCity()).append("</td>")
                    .append("<td>").append(ssd.getSpot()).append("</td>")
              .append("</tr>");
        }
        sb.append("</tbody>\n" +
                "    </table>\n" +
                "</body>\n" +
                "</html>");

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.getWriter().println(sb.toString());
        resp.getWriter().flush();
    }
}
