public class StringTest3{

    public static void main(String[] args) {
        // String str1 = "he";
        // String str2 = "hello";

        // System.out.println(str2.contains(str1));
        // System.out.println(firstCase("hello"));
        String str = "hello";
        str += "world";
        System.out.println(str);
    }

    public static String firstCase(String str){
        return str.substring(0,1).toUpperCase()+str.substring(1);
    }
}