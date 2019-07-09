// class Person{
//     public void fun(){
//         System.out.println("我是爹");
//     }

//     public void fan(){
//         System.out.println("爷爷");
//     }
// }

// class Student extends Person{
//     public void fun(){
       
//         System.out.println("我是儿子");
//          // 明确调用父类的fun方法
//          super.fan();
//     }
// }
class Person{
    public String name = "Person";
    public int age = 20;
}

public class ClassAndObjectTest{

    public final int a = 0;
    public static void main(String[] args){
        new Student().fun();
        a = 2;
    }
}

