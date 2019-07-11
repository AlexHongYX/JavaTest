package com.xiaoaxiao.test.methodReferenceTest;

/**
 * Created by xiaoaxiao on 2019/7/11
 * Description: 方法引用——引用类中构造方法
 */

class Person{
    private String name;
    private Integer age;

    public Person(String name,Integer age){
        super();
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

@FunctionalInterface
interface IUtil4<R,P1,P2>{
    R createPerson(P1 p1,P2 p2);
}

public class MethodTest4 {

    public static void main(String[] args) {
        IUtil4<Person,String,Integer> iUtil4 = Person::new;
        Person person = iUtil4.createPerson("xiaoaxiao",99);
        System.out.println(person);
    }
}
