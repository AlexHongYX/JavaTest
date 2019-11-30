package com.xiaoaxiao.tang_peotry_analysis.servlet;

import com.xiaoaxiao.tang_peotry_analysis.analyze.model.AuthorCount;
import com.xiaoaxiao.tang_peotry_analysis.analyze.service.AnalyzeService;
import com.xiaoaxiao.tang_peotry_analysis.config.ObjectFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/11/30
 * Description: 作者及其作品数量web接口
 */
public class AuthorCountServlet extends HttpServlet {

    private AnalyzeService analyzeService = ObjectFactory.getInstance().getObject(AnalyzeService.class);

    private List<AuthorCount> analyzeAuthorCount(){
        return this.analyzeService.analyzeAuthorCount();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        System.out.println("Hello world");
        out.println("Hello World");
        out.println(analyzeAuthorCount());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
