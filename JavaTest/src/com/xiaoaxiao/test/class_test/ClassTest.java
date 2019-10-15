package com.xiaoaxiao.test.class_test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by xiaoaxiao on 2019/10/15
 * Description:
 */

class Person{
    private int age;
    private String name;
    public int s;

    public Person(){
        System.out.println("This is person");
    }

    public Person(int age){
        System.out.println("This is person has age:"+age);
    }

    public void sleep(){
        System.out.println("sleep");
    }

    public void eat(){
        System.out.println("eat");
    }
}

public class ClassTest {

    public static void main(String[] args) throws Exception {
        Class<Person> personClass = (Class<Person>)Class.forName("com.xiaoaxiao.test.class_test.Person");
        Person person = personClass.newInstance();
        Constructor constructor = personClass.getConstructor(int.class);
        Person person1 = (Person) constructor.newInstance(15);

        Field field = personClass.getDeclaredField("age");
        System.out.println(field.get(person));
    }
}
