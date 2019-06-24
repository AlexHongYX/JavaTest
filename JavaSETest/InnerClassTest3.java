/**
 * 验证方法内部类及匿名内部类的使用
 */
interface MyInterface{
    void test();
}

class Outter{
    private int num = 5;

    public void display(int temp){
        
        // // 方法内部类
        // class Inner{
        //     public void fun(){
        //         System.out.println(num);
        //         System.out.println(temp);
        //     }
        // }
        // new Inner().fun();

        // 匿名内部类，匿名的实现了MyInterface接口
        // new MyInterface(){
        //     @Override
        //     public void test() {
        //         System.out.println("匿名实现MyInterface接口");
        //         System.out.println(temp);
        //     }
        // }.test();
        new MyInterface(){
            @Override
            public void test(){
                System.out.println("匿名内部类："+temp+","+num);
            }
        }.test();
    }
}

public class InnerClassTest3{
    public static void main(String[] args) {
        new Outter().display(10);;
    }
}