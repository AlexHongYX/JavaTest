package com.xiaoaxiao.test.collection_test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by xiaoaxiao on 2019/7/27
 * Description: 要实现HashSet，需要覆写hashCode()和equals()
 */

class Employee{
    private String name;
    private Integer age;

    public Employee(String name, int age) {
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
    public int hashCode() {
        return Objects.hash(name,age);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if(!(obj instanceof Employee)){
            return false;
        }

        Employee employee = (Employee)obj;
        return this.name.equals(employee.name)&&this.age.equals(employee.age);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class HashSetTest {

    public static void main(String[] args) {
        Set<Employee> employees = new HashSet<>();

        employees.add(new Employee("张三",10));
        employees.add(new Employee("李四",20));
        employees.add(new Employee("王五",30));
        employees.add(new Employee("张三",10));

        System.out.println(employees);
    }
}
