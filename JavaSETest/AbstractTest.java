// 若抽象类的子类还是一个抽象类，则覆写不覆写，覆写几个都无所谓
// 只要最终的子类把直接或间接的父类的抽象方法都能实现即可（无论是继承了父类的还是在子类中定义的）
abstract class Person{
    private String name;

    public String getName(){
        return this.name;
    }

    // 抽象方法
    public abstract void getPersonInfo();

    public abstract void test();
}

abstract class Student extends Person{
    public void getPersonInfo(){
        System.out.println("PersonInfo");
    }

    public abstract void fun();
}

class Test extends Student{
    public void fun(){
        System.out.println("fun");
    }

    public void test(){
        System.out.println("test");
    }
}

public class AbstractTest{
    public static void main(String[] args) {
        // Person person = new Person();
        // new Student();
        Test test = new Test();
        test.fun();
        test.test();
        test.getPersonInfo();
    }    
}