/**
 * 多态的使用以及instanceof关键字的使用
 */
class Person{
    public void fun(){
        System.out.println("我是人类");
    }
}

class Student extends Person{
    public void fun(){
        System.out.println("我是学生");
    }
}

class Worker extends Person{
    public void fun(){
        System.out.println("我是工人");
    }
}
public class PolymorphismTest{
    public static void main(String[] args){
        // Person per = new Person();
        
        // System.out.println(per instanceof Person);
        // if(!(per instanceof Student)){
        //     per = new Student();
        //     System.out.println(per instanceof Student);
        // }
        
        // Student stu = (Student)per;
        // stu.fun();
        // test(new Person());
        // test(new Student());
        // test(new Worker());
        // Student stu = new Student();
        // Person per = new Person();
        // System.out.println(stu instanceof Person);
        // System.out.println(per instanceof Student);
        // Student stu = new Person(); //直接向下转型错误

        Person per = new Student();
        Student stu = (Student)per;
    }

    public static void test(Person per){
        per.fun();
    }
}