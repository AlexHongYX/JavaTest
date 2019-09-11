package com.xiaoaxiao.binary_search_test;

/**
 * Created by xiaoaxiao on 2019/9/10
 * Description: MyTreeMap的测试类
 */

import java.util.Comparator;

/**
 * 创建一个实现了Comparable接口的类
 */
class Person implements Comparable<Person>{

    public String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }
}

class Contact {
    public String phone;
    public String address;

    public Contact(String phone, String address) {
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

class PersonComparator implements Comparator<Person>{

    @Override
    public int compare(Person o1, Person o2) {
        return o1.name.compareTo(o2.name);
    }
}

public class TreeMapTest {
    public static void main(String[] args) {
        MyTreeMap<Person,Contact> myTreeMap = new MyTreeMap<>(new PersonComparator());
        Person person = new Person("xiao");
        Contact contact = new Contact("123","world");
        myTreeMap.put(person,contact);
        System.out.println(myTreeMap.get(person));
    }
}
