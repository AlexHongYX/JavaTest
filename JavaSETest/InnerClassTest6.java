/**
 * final修饰外部类，该类不能被extends，但要是内部类被final修饰，而外部类被继承，则该内部类自然而然的也会出现在外部类的子类中。
 */
class Father{

    final String msg = "test";
    final class Inner{
        public String msg = "inner";
    }
}

class Child extends Father{

    public void test(){
        System.out.println(msg);
        // Inner inner = new Inner();
        // System.out.println(inner.msg);
    }
}

public class InnerClassTest6{
    public static void main(String[] args) {
        // Child child = new Child();
        // child.test();
        Child.Inner inner = new Child().new Inner();
        System.out.println(inner.msg);
    }
   
}