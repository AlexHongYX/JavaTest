package string;

import java.util.Stack;

/**
 * Created by xiaoaxiao on 2019/11/4
 * Description: 单词逆置
 */
public class Reverse {
    public static String ReverseSentence(String str) {
        if(str.equals("")){
            return "";
        }
        Stack<String> stack = new Stack<>();

        String[] strings = str.split(" ");
        if(strings.length==0){
            return str;
        }

        for(String s:strings){
            stack.push(s);
        }


        StringBuilder newString = new StringBuilder();
        while(!stack.isEmpty()){
            newString.append(stack.pop());
            if(!stack.isEmpty()){
                newString.append(" ");
            }
        }
        return newString.toString();
    }

    public static void main(String[] args) {
//        System.out.println(ReverseSentence("student. a am I"));
        System.out.println(ReverseSentence("     "));

    }
}
