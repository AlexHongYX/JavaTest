package com.xiaoaxiao.test.reflect_test.reflect_vo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xiaoaxiao on 2019/7/21
 * Description: 实现自动的vo匹配处理操作
 */
public class BeanOperation {

    public BeanOperation(){}

    /**
     * 负责设置类中的属性操作
     * @param actionObject  表示当前发出设置请求的程序类的当前对象
     * @param msg   属性的具体内容，格式为：emp.name:xiaoaxiao|emp.job:Java Coder
     */
    public static void setBeanValue(Object actionObject,String msg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        // 1、对字符串进行拆分
        String[] temp = msg.split("\\|");
        // emp.name:xiaoaxiao
        // emp.job:Java Coder
        for (int i=0;i<temp.length;i++){
            String[] result = temp[i].split(":");
            // emp.name
            // xiaoaxiao
            // 取得真实要设置的内容 xiaoxiao/Java Coder
            String realValue = result[1];

            // 取得要设置的真实类名称  emp
            String realClassName = result[0].split("\\.")[0];

            // 取得要设置的属性名称   name/job
            String attrName = result[0].split("\\.")[1];

            // 取得反射取得xxAction中的真实对象 emp独享
            Object realObj = getRealObject(actionObject,realClassName);
//            System.out.println(realObj);

            // 再利用反射设置emp对象的属性
            // 1、取得emp class对象
            Class<?> cls = realObj.getClass();

            // 2、拼装setter方法名称
            String setName = "set"+initCap(attrName);

            // 3、生成setName的Method对象
            Method method = cls.getDeclaredMethod(setName,String.class);

            // 4、使用invoke()调用Setter方法
            method.invoke(realObj,realValue);
            System.out.println(realObj);
        }

    }

    /**
     * 根据actionObj获取真实对象
     * 通过反射调用getEmp方法，获取真实emp对象
     * @param actionObj
     * @param realClassName
     * @return
     */
    public static Object getRealObject(Object actionObj,String realClassName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // 取得actionObj的Class对象
        Class<?> cls = actionObj.getClass();

//        System.out.println(realClassName);

        // 拼装getEmp方法名
        String methodName = "get"+ initCap(realClassName);

        // 获取getEmp方法的Method对象
        Method method = cls.getDeclaredMethod(methodName);

        // 通过反射获取obj对象（相当于调用actionObj.getEmp()）
        Object realObj = method.invoke(actionObj);

//        System.out.println(realObj);

        return realObj;
    }

    /**
     *
     */
    public static String initCap(String str){
        return str.substring(0,1).toUpperCase()+str.substring(1);
    }
}
