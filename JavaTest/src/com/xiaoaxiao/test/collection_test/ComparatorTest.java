package com.xiaoaxiao.test.collection_test;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by xiaoaxiao on 2019/7/27
 * Description: 自定义Comparator"比较器"
 */

class Student{
    private String name;
    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class AscAgeComparator implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
        // 若o1>o2，返回1，o1成了o2的右子树
        // 最终中序遍历的时候，顺序就成了从小到大遍历
        return o1.getAge()-o2.getAge();
    }
}

class DescAgeComparator implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
        // 若o1<o2，返回1，o1成了o2的右子树
        // 最终中序遍历的时候，顺序就成了从大到小遍历
        return o2.getAge()-o1.getAge();
    }
}

public class ComparatorTest {

    public static void main(String[] args) {
        Set<Student> studentSet = new TreeSet<>(new AscAgeComparator());

        studentSet.add(new Student("hello",10));
        studentSet.add(new Student("world",40));
        studentSet.add(new Student("Hello",20));
        studentSet.add(new Student("Hello",20));

        System.out.println(studentSet);
    }
}
