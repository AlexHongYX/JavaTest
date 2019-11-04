package string;

/**
 * Created by xiaoaxiao on 2019/11/4
 * Description: 字符串转换为整数
 */
public class Transfer {
    public static int StrToInt(String str) {
        long result = 0;
        boolean flag = true;
        if (str.charAt(0) == '-') {
            flag = false;
        }
        str = str.substring(1);
        for(char c:str.toCharArray()){
            if (flag){
                result = result*10+(c-48);
            }else {
                result = result*10-(c-48);
            }
        }
        if(flag){
            if(result>Integer.MAX_VALUE){
                return 0;
            }else{
                return (int)result;
            }
        }else{
            if(result>(long)Integer.MAX_VALUE+1){
                return 0;
            }else{
                return (int)result*(-1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(StrToInt("-123"));
    }
}
