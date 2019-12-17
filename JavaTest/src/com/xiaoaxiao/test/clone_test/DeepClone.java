package com.xiaoaxiao.test.clone_test;

/**
 * Created by xiaoaxiao on 2019/12/17
 * Description: 深拷贝
 *          要想实现深拷贝，就得在当前对象的clone()方法中将其属性对象也完全拷贝一份，
 *          这就要求其属性对象必须覆写clone()
 */

class Animal implements Cloneable{
    private Body body;

    public Animal(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Animal newAnimal = (Animal)super.clone();
        // 引用数据类型的属性进行拷贝
        Body newBody = (Body)body.clone();
        newAnimal.setBody(newBody);
        return newAnimal;
    }
}

class Body implements Cloneable{

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class DeepClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        Animal animal1 = new Animal(new Body());
        Animal animal2 = (Animal)animal1.clone();

        System.out.println(animal1==animal2);
        System.out.println(animal1.getBody()==animal2.getBody());
    }
}
