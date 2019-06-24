/**
 * 使用内部类实现“多继承”
 */
class A{
    private String name = "name";
    public String getName(){
        return this.name;
    }
} 

class B{
    private int age = 15;
    public int getAge(){
        return this.age;
    }
}

class Person{
    class Name extends A{
        public String getName(){
            return super.getName();
        }
    }

    class Age extends B{
        public int getAge(){
            return super.getAge();
        }
    }

    public String getName(){
        return new Name().getName();
    }

    public int getAge(){
        return new Age().getAge();
    }
}

public class InnerClassTest{
    public static void main(String[] args) {
        Person person = new Person();
        System.out.println(person.getName()+":"+person.getAge());
    }
}