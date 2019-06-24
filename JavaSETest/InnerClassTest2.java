/**
 * 验证内部类与外部类可以方便的访问彼此的私有域
 */
class Outter{
    private String msg = "Outter";

    //---------------------
    // 内部类
    class Inner{
        private String inMsg = "Inner";
        public void fun(){
            // 直接调用外部类的私有属性
            System.out.println(msg);
        }
    }
    //---------------------

    public void test(){
        Inner in = new Inner();
        in.fun();
        System.out.println(in.inMsg);
    }
}


public class InnerClassTest2{
    public static void main(String[] args) {
        Outter outter = new Outter();
        outter.test();
    }
}