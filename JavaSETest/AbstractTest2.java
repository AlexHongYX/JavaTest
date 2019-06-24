// 子类不用管父类中的内部抽象类，只需要管父类的直接抽象方法
abstract class Person{
    private String name;

    public Person(){
        System.out.println("Person");
    }

    public String getName(){
        return this.name;
    }

    // 抽象方法
    public abstract void getPersonInfo();

}

// 抽象类中没有抽象方法仍不能直接创建实例
abstract class Test{
    public void fun(){
        System.out.println("fun");
    }

}

class Student extends Person{

    public Student(){
        System.out.println("Student");
    }

    public void getPersonInfo(){
        System.out.println("PersonInfo");
    }
}

// 内部抽象类
abstract class Animal{

    public abstract void test1();

    abstract class B{
        public abstract void test2();
    }
}

class Dog extends Animal{
    public void test1(){
        System.err.println("test1");
    }
}

public class AbstractTest2{
    public static void main(String[] args) {
        // Person per = new Student();
        // per.getPersonInfo();
        // new Student();
        // new Test(); //不能直接创建抽象类的实例——不管其有没有抽象方法
        new Dog().test1();;
    }    
}