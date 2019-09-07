package com.xiaoaxiao.myfirst.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xiaoaxiao on 2019/9/7
 * Description: 每次请求除了记住uri外，添加请求计数器(牵扯到多线程访问)
 *              即便是将tomcat重启，key-value的值也是在累加
 */
public class CounterFilter implements Filter {

    private File logFile;

    // 创建一个单线程线程池——保证任务有序且线程安全的执行
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String filename = filterConfig.getInitParameter("filter_filename");
        String webAppRoot = filterConfig.getServletContext().getRealPath("/");
        this.logFile = new File(webAppRoot,filename);
        if (!this.logFile.exists()){
            try {
                this.logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String uri = ((HttpServletRequest)request).getRequestURI();
        final File propertiesFile = this.logFile;
        executorService.execute(new Runnable() {
            /**
             * 利用Properties类的key-value属性，先将文件内容读到Properties对象中
             * 进行key-value的相关操作后，再将Properties对象中的内容写回到文件
             */
            @Override
            public void run() {
                // 使用Properties类保存key-value形式的数据
                Properties properties = new Properties();
                try {
                    properties.load(new FileInputStream(propertiesFile));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String value = properties.getProperty(uri);
                if(value==null){
                    value = "1";
                }else {
                    value = String.valueOf((Integer.parseInt(value)+1));
                }
                properties.setProperty(uri,value);
                // 再将该Properties形式写回到文件中
                try {
                    properties.store(new FileOutputStream(propertiesFile),"update");
                }  catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // 继续执行接下来的过滤器/Servlet
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // do nothing
    }
}
