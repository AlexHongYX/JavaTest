package com.xiaoaxiao.test.io_test.serializable_test;

import java.io.*;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by xiaoaxiao on 2019/7/27
 * Description: Serializable序列化与反序列化
 */

class Person implements Serializable{

    private String name;
    private transient int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class SerializableTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // 1、获取文件对象，并新建一个实现了Serializable接口的类的对象
        File file = new File("C:"+File.separator+"Users"+File.separator+
                "xiaoaxiao"+File.separator+"Desktop"+File.separator+"Test.txt");
        /**
         * 序列化
         */
//        Person person = new Person("hello",99);
//        // 2、获取相应输出流
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
//
//        // 3、将对象序列化输出
//        oos.writeObject(person);
//
//        // 4、关闭流
//        oos.close();
        /**
         * 反序列化
         */
        // 2、获取相应输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

        // 3、将对象反序列化输入
        Object obj = ois.readObject();

        System.out.println(obj);
        // 4、关闭流
        ois.close();
    }
}
