package com.xiaoaxiao.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaoaxiao on 2019/12/11
 * Description:
 */
@Controller
public class HelloController {

    @RequestMapping(value = {" ","index"},method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        Map<String,String> info = new HashMap<String, String>();
        info.put("datetime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        modelAndView.addAllObjects(info);
        return modelAndView;
    }
}
