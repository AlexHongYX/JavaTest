public class StringTest{
    public static void main(String[] args) {
        // // 直接赋值
        // String str = "hello";
        // // 通过构造方法实例化String对象
        // String str2 = new String("hello");
        // // 直接相比相当于是在比较地址
        // System.out.println(str==str2);
        char c = "hello".charAt(1);
        System.out.println(c-32);
    }
}