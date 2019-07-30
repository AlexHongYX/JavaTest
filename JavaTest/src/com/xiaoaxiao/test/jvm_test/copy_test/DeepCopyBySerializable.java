package com.xiaoaxiao.test.jvm_test.copy_test;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;

/**
 * Created by xiaoaxiao on 2019/7/30
 * Description: 深拷贝测试——实现Serializable接口<推荐>
 */
public class DeepCopyBySerializable {
    static class Teacher implements Serializable {
        private String name;
        private String direction;

        public Teacher(String name, String direction) {
            this.name = name;
            this.direction = direction;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "Teacher{" +
                    "name='" + name + '\'' +
                    ", direction='" + direction + '\'' +
                    '}';
        }
    }

    static class Student implements Serializable{
        private String name;
        private Integer age;
        private Teacher teacher;

        // 实现深拷贝,序列化前与序列化后是两个属性相同的对象，应用于深拷贝
        public Student cloneObject() throws Exception{
            // 1、先将对象通过序列化的方式输出到内存流中
            // 获取内存输出流
            ByteOutputStream bos = new ByteOutputStream();
            // 获取序列化的字节输出流
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            // 将当前对象输出到内存流中
            oos.writeObject(this);

            // 2、通过反序列化的方式从内存流中获取新的对象
            //    经过序列化与反序列化的操作，得到的对象是和原来对象属性相同的新对象
            // 获取反序列化的字节输入流，并传入刚才内存输出流中保存对象的字节数组
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.getBytes());
            // 获取反序列化的字节输入流
            ObjectInputStream ois = new ObjectInputStream(bis);
            // 获取一个新对象并返回
            return (Student)ois.readObject();
        }

        public Student(String name, Integer age, Teacher teacher) {
            this.name = name;
            this.age = age;
            this.teacher = teacher;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Teacher getTeacher() {
            return teacher;
        }

        public void setTeacher(Teacher teacher) {
            this.teacher = teacher;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", teacher=" + teacher +
                    '}';
        }
    }

    public static void main(String[] args) {
        Teacher teacher = new Teacher("Miss Wang","English Teacher");
        Student student = new Student("xiaoaxiao",18,teacher);


        Student cloneStudent = null;
        try {
            cloneStudent = student.cloneObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(cloneStudent.getName());
        System.out.println(cloneStudent.getAge());

        // 通过深拷贝，两个不同的student
        System.out.println(cloneStudent==student);

        // 对应的也是不同的两个teacher
        System.out.println(cloneStudent.getTeacher()==student.getTeacher());
    }
}
