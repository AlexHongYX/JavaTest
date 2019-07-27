package com.xiaoaxiao.test.reflect_test.reflect_vo;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by xiaoaxiao on 2019/7/21
 * Description: Emp类的操作类
 */
public class EmpAction {

    // 必须得new一个对象出来，不然后面调用empAction.get返回的是null
    // 只有new一个对象出来，返回的才是Emp对象
    private Emp emp = new Emp();

    public void setValue(String value){
        try {
            BeanOperation.setBeanValue(this,value);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Emp getEmp() {
        return emp;
    }
}
