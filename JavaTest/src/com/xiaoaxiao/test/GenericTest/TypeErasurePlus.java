package com.xiaoaxiao.test.GenericTest;

import java.lang.reflect.Field;

/**
 * Created by xiaoaxiao on 2019/7/10
 * Description: 泛型类的类型参数若没有指定上限，会被擦除为Object类型。
 *              如果指定上限，则类型参数被替换为相应的类型上限。
 */

class MyClass<T,E extends Number>{
    private T message;
    private E text;

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public E getText() {
        return text;
    }

    public void setText(E text) {
        this.text = text;
    }
}

public class TypeErasurePlus {

    public static void main(String[] args) {
        MyClass<String,Integer> myClass = new MyClass<>();
        Class cls = myClass.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field:fields) {
            System.out.println(field.getType());
        }
    }
}
