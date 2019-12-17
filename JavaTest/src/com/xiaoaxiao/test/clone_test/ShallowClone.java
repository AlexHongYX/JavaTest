package com.xiaoaxiao.test.clone_test;

/**
 * Created by xiaoaxiao on 2019/12/17
 * Description: 浅拷贝
 *          直接调用当前对象的clone()，当前对象会是直接被拷贝，
 *          但当前对象内部属性的对象不一定会被拷贝(只是引用传递)
 */

class Person implements Cloneable{

    private Face face;
    private int age;


    public Person(Face face, int age) {
        this.face = face;
        this.age = age;
    }

    public Face getFace() {
        return face;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Face{

}

public class ShallowClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person person1 = new Person(new Face(),30);

        Person person2 = (Person)person1.clone();

        System.out.println(person1==person2);
        System.out.println(person1.getFace()==person2.getFace());
    }

}
