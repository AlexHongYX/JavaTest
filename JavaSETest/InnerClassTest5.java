/**
 * 验证静态内部类
 */
class Outter{
    // private String name = "name";
    private static int age = 15;

    static class Inner{
        private String username = "username";
        private static String password = "password";
        public void test(){
            // System.out.println(name+":"+age);  //静态内部类不可使用外部类的非静态域
            System.out.println(username+","+password+","+age);
        }
    }
}

public class InnerClassTest5{
    public static void main(String[] args) {
        Outter.Inner inner = new Outter.Inner();
        inner.test();
    }
}