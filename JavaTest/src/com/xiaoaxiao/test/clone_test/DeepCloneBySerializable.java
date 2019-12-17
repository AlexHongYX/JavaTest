package com.xiaoaxiao.test.clone_test;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;

/**
 * Created by xiaoaxiao on 2019/12/17
 * Description: 通过序列化与反序列化实现深拷贝
 */

class Student implements Serializable{
    private Teacher teacher;

    public Student(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Student cloneObject() throws IOException, ClassNotFoundException {
        // 通过内存流进行序列化，将对象序列化为字节数组存放在内存中
        ByteOutputStream baos = new ByteOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);

        // 通过内存流进行反序列化，将内存中的字节数组反序列化为对象

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.getBytes());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Student newStu = (Student)ois.readObject();
        return newStu;
    }
}

class Teacher implements Serializable{

}

public class DeepCloneBySerializable  {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student(new Teacher());

        Student newStu = student.cloneObject();

        System.out.println(student==newStu);
        System.out.println(student.getTeacher()==newStu.getTeacher());
    }
}
