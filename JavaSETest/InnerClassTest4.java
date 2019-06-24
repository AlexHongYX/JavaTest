/**
 * 验证内部类的调用方式以及访问方式
 */
class Outter1{
    private String name = "hello";

    class Inner{
        public void test(){
            System.out.println(name);
        }
    }

    public void test(){
        Inner inner = new Inner();
        inner.test();
    }
}

class Outter2{
    private int age = 15;

    class Inner{
        public void test(){
            // 内部类中修改的依旧是外部类的那个属性
            age = 20;
            System.out.println(age);
        }
    }

    public void test(){
        
    }
}

public class InnerClassTest4{
    public static void main(String[] args) {
        // Outter1 outter = new Outter1();
        // outter.test();
        Outter2.Inner inner2 = new Outter2().new Inner();
        inner2.test();
    }
}