package com.xiaoaxiao.test.collection_test;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by xiaoaxiao on 2019/7/27
 * Description: Comparable接口测试
 */

class Animal implements Comparable<Animal>{

    private String name;
    private Integer age;

    public Animal(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
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
    public int compareTo(Animal o) {
        if (this.getAge()>o.getAge()){
            return 1;
        }else if (this.getAge()<o.getAge()){
            return -1;
        }else {
            return this.getName().compareTo(o.getName());
        }
    }
}

public class ComparableTest {

    public static void main(String[] args) {
        Set<Animal> animalSet = new TreeSet<>();

        animalSet.add(new Animal("dog",30));
        animalSet.add(new Animal("cat",20));
        animalSet.add(new Animal("pig",40));
        animalSet.add(new Animal("horse",10));
        animalSet.add(new Animal("horse",10));

        System.out.println(animalSet);
    }
}
