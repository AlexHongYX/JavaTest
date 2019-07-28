package com.xiaoaxiao.test.collection_test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/7/27
 * Description: List基本测试(equals()方法的覆写）
 */

class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Person)){
            return false;
        }

        Person per = (Person) obj;

        return this.age==per.age&&this.name.equals(per.name);
    }
}

public class ListTest {

    public static void main(String[] args) {

//        List<String> list = new ArrayList<>();
//
//        System.out.println(list.size()+"、"+list.isEmpty());
//
//        list.add("hello");
//        list.add("world");
//        list.add("hello");
//        System.out.println(list);
//        System.out.println(list.contains("world"));
//        System.out.println(list.remove("world"));
//        System.out.println(list);
//
//        list.set(1,"world");
//
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("a",1));
        personList.add(new Person("b",2));
        personList.add(new Person("c",3));

        System.out.println(personList);

        personList.remove(new Person("b",2));

        System.out.println(personList);

    }
}
