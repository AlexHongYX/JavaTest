package com.xiaoaxiao.test.reflect_test.reflect_vo;

/**
 * Created by xiaoaxiao on 2019/7/21
 * Description: 反射VO测试类
 */
public class Test {

    public static void main(String[] args) {
        String value = "emp.name:xiaoaxiao|emp.job:Java Coder";
        EmpAction empAction = new EmpAction();
        empAction.setValue(value);

//        System.out.println(empAction.getEmp());
    }
}
