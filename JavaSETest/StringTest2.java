public class StringTest2{
 
    public static void main(String[] args) {
        String str = "ab";
        if(isNumber(str)){
            System.out.println("有数字");
        }else{
            System.out.println("无数字");
        }
    }

    public static boolean isNumber(String str){
        char[] chars = str.toCharArray();
        for(char c:chars){
            if(c>='0'&&c<='9'){
                return true;
            }
        }
        return false;
    }
}