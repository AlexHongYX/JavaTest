package test;

/**
 * Created by xiaoaxiao on 2019/11/13
 * Description:
 */

class Animal{
    public void move(){
        System.out.println("a");
    }
}

class Dog extends Animal{
    public void move(){
        System.out.println("d1");
    }

    public void bark(){
        System.out.println("d2");
    }
}

public class Main {

    public static void main(String[] args) {
        Animal a = new Animal();
        Animal b = new Dog();
        a.move();
        b.move();
        ((Dog) b).bark();
    }
}
